package com.example.prodoreviewer;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.tensorflow.lite.DataType;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class aiTest extends AppCompatActivity {

    private Interpreter tfliteCS; // For CS model
    private Interpreter tfliteIT; // For IT model

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_test);

        // Load the TFLite models
        try {
            tfliteCS = new Interpreter(loadModelFile("CS_gwa_model.tflite"), new Interpreter.Options());
            tfliteIT = new Interpreter(loadModelFile("IT_gwa_model.tflite"), new Interpreter.Options());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Find UI elements
        EditText introversion = findViewById(R.id.introversion);
        EditText extroversion = findViewById(R.id.extroversion);
        EditText intuition = findViewById(R.id.intuition);
        EditText sensing = findViewById(R.id.sensing);
        EditText thinking = findViewById(R.id.thinking);
        EditText feeling = findViewById(R.id.feeling);
        EditText judging = findViewById(R.id.judging);
        EditText perceiving = findViewById(R.id.perceiving);
        Button predictButton = findViewById(R.id.predictButton);
        TextView itResult = findViewById(R.id.itResult);
        TextView csResult = findViewById(R.id.csResult);

        predictButton.setOnClickListener(v -> {
            float[] inputValues = new float[8];
            inputValues[0] = Float.parseFloat(introversion.getText().toString());
            inputValues[1] = Float.parseFloat(extroversion.getText().toString());
            inputValues[2] = Float.parseFloat(intuition.getText().toString());
            inputValues[3] = Float.parseFloat(sensing.getText().toString());
            inputValues[4] = Float.parseFloat(thinking.getText().toString());
            inputValues[5] = Float.parseFloat(feeling.getText().toString());
            inputValues[6] = Float.parseFloat(judging.getText().toString());
            inputValues[7] = Float.parseFloat(perceiving.getText().toString());

            // Create input tensor buffers for CS and IT
            TensorBuffer inputBufferCS = TensorBuffer.createFixedSize(new int[]{1, 8}, DataType.FLOAT32);
            inputBufferCS.loadArray(inputValues);

            TensorBuffer inputBufferIT = TensorBuffer.createFixedSize(new int[]{1, 8}, DataType.FLOAT32);
            inputBufferIT.loadArray(inputValues);

            // Predict GWA for CS
            float csPrediction = doInference(tfliteCS, inputBufferCS);
            csResult.setText("Predicted GWA for CS: " + csPrediction);

            // Predict GWA for IT
            float itPrediction = doInference(tfliteIT, inputBufferIT);
            itResult.setText("Predicted GWA for IT: " + itPrediction);
        });
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
