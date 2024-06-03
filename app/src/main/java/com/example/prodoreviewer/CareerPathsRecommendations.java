package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.tensorflow.lite.DataType;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class CareerPathsRecommendations extends AppCompatActivity {

    String UserEmail, personalityCode = "";
    ImageButton btnHome, btnBack;
    TextView txtLabel, txtProgramRecommendation;

    DatabaseHelper db;
    private Interpreter tfliteCS; // For CS model
    private Interpreter tfliteIT; // For IT model

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_paths_recommendations);

        db = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBackButton);
        btnHome = findViewById(R.id.btnHome);
        txtLabel = findViewById(R.id.lblPageName);
        txtProgramRecommendation = findViewById(R.id.txtProgramRecommendation);

        txtLabel.setText("Career Paths - Recommendations");

        Intent intent = getIntent();
        if (intent != null) {
            UserEmail = intent.getStringExtra("email");
            personalityCode = intent.getStringExtra("mbtiType"); // Retrieve personality code from intent
        }

        String world = "" + (db.getPersonality(UserEmail).charAt(0));
        String information = "" + (db.getPersonality(UserEmail).charAt(1));
        String decision = "" + (db.getPersonality(UserEmail).charAt(2));
        String structure = "" + (db.getPersonality(UserEmail).charAt(3));

        float extroversionp;
        float introversionp;
        float intuitionp;
        float sensingp;
        float thinkingp;
        float feelingp;
        float judgingp;
        float perceivingp;

        int percentW = db.getPercentage(UserEmail, "world");
        int percentI = db.getPercentage(UserEmail, "information");
        int percentD = db.getPercentage(UserEmail, "decisions");
        int percentS = db.getPercentage(UserEmail, "structure");

        if (world.equals("E")) {
            extroversionp = percentW;
            introversionp = 100 - percentW;
        } else {
            introversionp = percentW;
            extroversionp = 100 - percentW;
        }

        if (information.equals("S")) {
            sensingp = percentI;
            intuitionp = 100 - percentI;
        } else {
            intuitionp = percentI;
            sensingp = 100 - percentI;
        }

        if (decision.equals("T")) {
            thinkingp = percentD;
            feelingp = 100 - percentD;
        } else {
            feelingp = percentD;
            thinkingp = 100 - percentD;
        }

        if (structure.equals("J")) {
            judgingp = percentS;
            perceivingp = 100 - percentS;
        } else {
            perceivingp = percentS;
            judgingp = 100 - percentS;
        }

        // Load the TFLite models
        try {
            tfliteCS = new Interpreter(loadModelFile("CS_gwa_model.tflite"), new Interpreter.Options());
            tfliteIT = new Interpreter(loadModelFile("IT_gwa_model.tflite"), new Interpreter.Options());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retrieve the user's personality traits from the database
        String personalityTraits = db.getPersonality(UserEmail);

        // Update button texts based on personality trait letters
        Button btnWorld = findViewById(R.id.btnWorld2);
        Button btnInformation = findViewById(R.id.btnInformation2);
        Button btnDecision = findViewById(R.id.btnDecision2);
        Button btnStructure = findViewById(R.id.btnStructure2);

        btnWorld.setText(String.valueOf(personalityTraits.charAt(0)));
        btnInformation.setText(String.valueOf(personalityTraits.charAt(1)));
        btnDecision.setText(String.valueOf(personalityTraits.charAt(2)));
        btnStructure.setText(String.valueOf(personalityTraits.charAt(3)));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareerPathsRecommendations.this, ForgeaMainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("email", UserEmail);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Perform GWA predictions
        float[] inputValues = new float[8];
        inputValues[0] = introversionp;
        inputValues[1] = extroversionp;
        inputValues[2] = intuitionp;
        inputValues[3] = sensingp;
        inputValues[4] = thinkingp;
        inputValues[5] = feelingp;
        inputValues[6] = judgingp;
        inputValues[7] = perceivingp;

        // Create input tensor buffers for CS and IT
        TensorBuffer inputBufferCS = TensorBuffer.createFixedSize(new int[]{1, 8}, DataType.FLOAT32);
        inputBufferCS.loadArray(inputValues);

        TensorBuffer inputBufferIT = TensorBuffer.createFixedSize(new int[]{1, 8}, DataType.FLOAT32);
        inputBufferIT.loadArray(inputValues);

        // Predict GWA for CS and IT
        float CSgwa = doInference(tfliteCS, inputBufferCS);
        float ITgwa = doInference(tfliteIT, inputBufferIT);

        // Use the predicted GWA values to set the program recommendation
        if (ITgwa > CSgwa) {
            txtProgramRecommendation.setText("Information Technology");
        } else {
            txtProgramRecommendation.setText("Computer Science");
        }
    }

    private MappedByteBuffer loadModelFile(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(getAssets().openFd(filename).getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = getAssets().openFd(filename).getStartOffset();
        long declaredLength = getAssets().openFd(filename).getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private float doInference(Interpreter interpreter, TensorBuffer inputBuffer) {
        float[][] output = new float[1][1];
        interpreter.run(inputBuffer.getBuffer(), output);
        return output[0][0];
    }
}
