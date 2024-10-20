package com.example.prodoreviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.tensorflow.lite.DataType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CareerPathsRecommendations extends AppCompatActivity {

    String UserEmail, personalityCode = "none";
    ImageButton btnHome, btnBack;
    TextView txtLabel, txtProgramRecommendation;

    DatabaseHelper db;
    PieChart pieChart;
    ScatterChart scatterChart;
    private Interpreter tfliteCS; // For CS model
    private Interpreter tfliteIT; // For IT model

    private int currentChartIndex = 0; // To keep track of the current chart
    private String[] traitCategories = {"introversion_extroversion", "intuition_sensing", "thinking_feeling", "judging_perceiving"};
    private List<String[]> csvData; // Store CSV data

    float CSgwa;
    float ITgwa;

    Long percentW, percentI, percentD, percentS;

    String world, information, decision, structure;

    String currentCategory = traitCategories[currentChartIndex];

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
        pieChart = findViewById(R.id.pieChart);
        scatterChart = findViewById(R.id.scatterChart);
        Button btnContinue = findViewById(R.id.btnContinue3);



        txtLabel.setText("Course Paths - Recommendations");

        Intent intent = getIntent();
        if (intent != null) {
            UserEmail = intent.getStringExtra("email");
            personalityCode = intent.getStringExtra("mbtiType"); // Retrieve personality code from intent
            percentW = intent.getLongExtra("percentW",0);
            percentI = intent.getLongExtra("percentI",0);
            percentD = intent.getLongExtra("percentD",0);
            percentS = intent.getLongExtra("percentS",0);


            Log.d("DEBUGGGGGGGGGG", personalityCode);
            world = "" + (personalityCode.charAt(0));
            information = "" + (personalityCode.charAt(1));
            decision = "" + (personalityCode.charAt(2));
            structure = "" + (personalityCode.charAt(3));
        }

        float extroversionp;
        float introversionp;
        float intuitionp;
        float sensingp;
        float thinkingp;
        float feelingp;
        float judgingp;
        float perceivingp;

        /*int percentW = db.getPercentage(UserEmail, "world");
        int percentI = db.getPercentage(UserEmail, "information");
        int percentD = db.getPercentage(UserEmail, "decisions");
        int percentS = db.getPercentage(UserEmail, "structure");*/


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

        // Load CSV data
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.students_dataset7);
            csvData = loadCSVData(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load the TFLite models
        try {
            tfliteCS = new Interpreter(loadModelFile("CS_gwa_model.tflite"), new Interpreter.Options());
            tfliteIT = new Interpreter(loadModelFile("IT_gwa_model.tflite"), new Interpreter.Options());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update button texts based on personality trait letters
        Button btnWorld = findViewById(R.id.btnWorld2);
        Button btnInformation = findViewById(R.id.btnInformation2);
        Button btnDecision = findViewById(R.id.btnDecision2);
        Button btnStructure = findViewById(R.id.btnStructure2);

        btnWorld.setText(String.valueOf(personalityCode.charAt(0)));
        btnInformation.setText(String.valueOf(personalityCode.charAt(1)));
        btnDecision.setText(String.valueOf(personalityCode.charAt(2)));
        btnStructure.setText(String.valueOf(personalityCode.charAt(3)));

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
        CSgwa = doInference(tfliteCS, inputBufferCS);
        ITgwa = doInference(tfliteIT, inputBufferIT);

        // Use the predicted GWA values to set the program recommendation
        if (ITgwa > CSgwa) {
            txtProgramRecommendation.setText("Information Technology");
        } else {
            txtProgramRecommendation.setText("Computer Science");
        }

        // Initial chart display
        updateCharts();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChartIndex = (currentChartIndex + 1) % traitCategories.length;
                updateCharts();
            }
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

//    private void updateCharts() {
//        if (currentChartIndex >= csvData.size()) {
//            currentChartIndex = 0;
//        }
//
//        // Accumulate trait values and GWA for IT and CS
//        float introversionSumIT = 0, extroversionSumIT = 0, itGwaSum = 0;
//        float introversionSumCS = 0, extroversionSumCS = 0, csGwaSum = 0;
//        int countIT = 0, countCS = 0;
//
//        for (String[] row : csvData) {
//            // Check if the row is for IT or CS
//            boolean isIT = "1".equals(row[8]);
//
//            // Parse trait values and GWA
//            float introversion = Float.parseFloat(row[0]);
//            float extroversion = Float.parseFloat(row[1]);
//            float gwa = Float.parseFloat(row[9]);
//
//            // Accumulate trait values and GWA based on the course
//            if (isIT) {
//                introversionSumIT += introversion;
//                extroversionSumIT += extroversion;
//                itGwaSum += gwa;
//                countIT++;
//            } else {
//                introversionSumCS += introversion;
//                extroversionSumCS += extroversion;
//                csGwaSum += gwa;
//                countCS++;
//            }
//        }
//
//        // Calculate averages for IT and CS
//        float avgIntroversionIT = introversionSumIT / countIT;
//        float avgExtroversionIT = extroversionSumIT / countIT;
//        float avgGwaIT = itGwaSum / countIT;
//
//        float avgIntroversionCS = introversionSumCS / countCS;
//        float avgExtroversionCS = extroversionSumCS / countCS;
//        float avgGwaCS = csGwaSum / countCS;
//
//        // Populate PieChart and ScatterChart with the averages
//        populateCharts(avgIntroversionIT, avgExtroversionIT, avgGwaIT, avgIntroversionCS, avgExtroversionCS, avgGwaCS);
//
//        currentChartIndex++;
//    }

    private void updateCharts() {
        Button btnContinue = findViewById(R.id.btnContinue3);
        // Handle different trait categories
        currentCategory = traitCategories[currentChartIndex];
        switch (currentCategory) {
            case "introversion_extroversion":
                populateIntroversionExtroversionCharts();
                btnContinue.setBackgroundResource(R.drawable.personality_world);
                break;
            case "intuition_sensing":
                populateIntuitionSensingCharts();
                btnContinue.setBackgroundResource(R.drawable.personality_information);
                break;
            case "thinking_feeling":
                populateThinkingFeelingCharts();
                btnContinue.setBackgroundResource(R.drawable.personality_decision);
                break;
            case "judging_perceiving":
                populateJudgingPerceivingCharts();
                btnContinue.setBackgroundResource(R.drawable.personality_structure);
                break;
        }
    }

    private void populateIntuitionSensingCharts() {
        float avgIntuitionIT = 0, avgSensingIT = 0, avgGwaIT = 0;
        float avgIntuitionCS = 0, avgSensingCS = 0, avgGwaCS = 0;
        int countIT = 0, countCS = 0;

        // Accumulate trait values and GWA for IT and CS for intuition and sensing
        for (String[] row : csvData) {
            // Check if the row is for IT or CS
            boolean isIT = "1".equals(row[8]);

            // Parse trait values and GWA
            float intuition = Float.parseFloat(row[2]);
            float sensing = Float.parseFloat(row[3]);
            float gwa = Float.parseFloat(row[9]); // Assuming GWA is always at index 9

            // Accumulate trait values and GWA based on the course
            if (isIT) {
                avgIntuitionIT += intuition;
                avgSensingIT += sensing;
                avgGwaIT += gwa;
                countIT++;
            } else {
                avgIntuitionCS += intuition;
                avgSensingCS += sensing;
                avgGwaCS += gwa;
                countCS++;
            }
        }

        // Calculate averages for IT and CS
        avgIntuitionIT /= countIT;
        avgSensingIT /= countIT;
        avgGwaIT /= countIT;

        avgIntuitionCS /= countCS;
        avgSensingCS /= countCS;
        avgGwaCS /= countCS;

        // Populate charts for intuition and sensing
        populateCharts(avgIntuitionIT, avgSensingIT, avgGwaIT, avgIntuitionCS, avgSensingCS, avgGwaCS,
                "Intuition", "Sensing", "Intuition", "Sensing", "Intuition vs Sensing");
    }

    private void populateThinkingFeelingCharts() {
        float avgThinkingIT = 0, avgFeelingIT = 0, avgGwaIT = 0;
        float avgThinkingCS = 0, avgFeelingCS = 0, avgGwaCS = 0;
        int countIT = 0, countCS = 0;

        // Accumulate trait values and GWA for IT and CS for thinking and feeling
        for (String[] row : csvData) {
            // Check if the row is for IT or CS
            boolean isIT = "1".equals(row[8]);

            // Parse trait values and GWA
            float thinking = Float.parseFloat(row[4]);
            float feeling = Float.parseFloat(row[5]);
            float gwa = Float.parseFloat(row[9]); // Assuming GWA is always at index 9

            // Accumulate trait values and GWA based on the course
            if (isIT) {
                avgThinkingIT += thinking;
                avgFeelingIT += feeling;
                avgGwaIT += gwa;
                countIT++;
            } else {
                avgThinkingCS += thinking;
                avgFeelingCS += feeling;
                avgGwaCS += gwa;
                countCS++;
            }
        }

        // Calculate averages for IT and CS
        avgThinkingIT /= countIT;
        avgFeelingIT /= countIT;
        avgGwaIT /= countIT;

        avgThinkingCS /= countCS;
        avgFeelingCS /= countCS;
        avgGwaCS /= countCS;

        // Populate charts for thinking and feeling
        populateCharts(avgThinkingIT, avgFeelingIT, avgGwaIT, avgThinkingCS, avgFeelingCS, avgGwaCS,
                "Thinking", "Feeling", "Thinking", "Feeling", "Thinking vs Feeling");
    }

    private void populateJudgingPerceivingCharts() {

        // Accumulate trait values and GWA for IT and CS
        float judgingSumIT = 0, perceivingSumIT = 0, itGwaSum = 0;
        float judgingSumCS = 0, perceivingSumCS = 0, csGwaSum = 0;
        int countIT = 0, countCS = 0;

        for (String[] row : csvData) {
            // Check if the row is for IT or CS
            boolean isIT = "1".equals(row[8]);

            // Parse trait values and GWA
            float judging = Float.parseFloat(row[6]);
            float perceiving = Float.parseFloat(row[7]);
            float gwa = Float.parseFloat(row[9]);

            // Accumulate trait values and GWA based on the course
            if (isIT) {
                judgingSumIT += judging;
                perceivingSumIT += perceiving;
                itGwaSum += gwa;
                countIT++;
            } else {
                judgingSumCS += judging;
                perceivingSumCS += perceiving;
                csGwaSum += gwa;
                countCS++;
            }
        }

        // Calculate averages for IT and CS
        float avgJudgingIT = judgingSumIT / countIT;
        float avgPerceivingIT = perceivingSumIT / countIT;
        float avgGwaIT = itGwaSum / countIT;

        float avgJudgingCS = judgingSumCS / countCS;
        float avgPerceivingCS = perceivingSumCS / countCS;
        float avgGwaCS = csGwaSum / countCS;

        // Populate PieChart and ScatterChart with the averages
        populateCharts(avgJudgingIT, avgPerceivingIT, avgGwaIT, avgJudgingCS, avgPerceivingCS, avgGwaCS,
                "Judging", "Perceiving", "Judging", "Perceiving", "Judging vs Perceiving");
    }

    private void populateIntroversionExtroversionCharts() {
        float avgIntroversionIT = 0, avgExtroversionIT = 0, avgGwaIT = 0;
        float avgIntroversionCS = 0, avgExtroversionCS = 0, avgGwaCS = 0;
        int countIT = 0, countCS = 0;

        // Accumulate trait values and GWA for IT and CS for introversion and extroversion
        for (String[] row : csvData) {
            // Check if the row is for IT or CS
            boolean isIT = "1".equals(row[8]);

            // Parse trait values and GWA
            float introversion = Float.parseFloat(row[1]);
            float extroversion = Float.parseFloat(row[2]);
            float gwa = Float.parseFloat(row[9]); // Assuming GWA is always at index 9

            // Accumulate trait values and GWA based on the course
            if (isIT) {
                avgIntroversionIT += introversion;
                avgExtroversionIT += extroversion;
                avgGwaIT += gwa;
                countIT++;
            } else {
                avgIntroversionCS += introversion;
                avgExtroversionCS += extroversion;
                avgGwaCS += gwa;
                countCS++;
            }
        }

        // Calculate averages for IT and CS
        avgIntroversionIT /= countIT;
        avgExtroversionIT /= countIT;
        avgGwaIT /= countIT;

        avgIntroversionCS /= countCS;
        avgExtroversionCS /= countCS;
        avgGwaCS /= countCS;

        // Populate charts for introversion and extroversion
        populateCharts(avgIntroversionIT, avgExtroversionIT, avgGwaIT, avgIntroversionCS, avgExtroversionCS, avgGwaCS,
                "Introversion", "Extroversion", "Introversion", "Extroversion", "Introversion vs Extroversion");
    }

    private void populateCharts(float avgTraitIT1, float avgTraitIT2, float avgGwaIT,
                                float avgTraitCS1, float avgTraitCS2, float avgGwaCS,
                                String traitLabelIT1, String traitLabelIT2,
                                String traitLabelCS1, String traitLabelCS2,
                                String chartDescription) {
        // Check which program is recommended based on GWA
        boolean isITRecommended = ITgwa > CSgwa;

        if (isITRecommended) {
            // Populate charts only with IT data
            populatePieChart(pieChart, avgTraitIT1, avgTraitIT2, traitLabelIT1, traitLabelIT2, "- IT Personality Data", chartDescription);
            populateScatterChart(scatterChart, csvData, getColumnIndex(currentCategory), avgGwaIT, Color.RED, Color.YELLOW, "IT GWA", "IT " + traitLabelIT1, "IT " + traitLabelIT2, traitLabelIT1, traitLabelIT2);
        } else {
            // Populate charts only with CS data
            populatePieChart(pieChart, avgTraitCS1, avgTraitCS2, traitLabelCS1, traitLabelCS2, "- CS Personality Data", chartDescription);
            populateScatterChart(scatterChart, csvData, getColumnIndex(currentCategory), avgGwaCS, Color.RED, Color.YELLOW, "CS GWA", "CS " + traitLabelCS1, "CS " + traitLabelCS2, traitLabelCS1, traitLabelCS2);
        }

    }

    private void populatePieChart(PieChart chart, float value1, float value2, String label1, String label2, String dataSetLabel, String description) {
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(value1, label1));
        pieEntries.add(new PieEntry(value2, label2));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, dataSetLabel);
        pieDataSet.setValueTextSize(14f); // Set value text size
        pieDataSet.setValueTextColor(Color.WHITE); // Set value text color to white
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        chart.setEntryLabelColor(Color.WHITE);
        chart.setData(pieData);
        chart.invalidate();

        Description chartDescription = new Description();
        chartDescription.setText(description);
        chartDescription.setTextColor(Color.WHITE);
        chartDescription.setTextSize(14f);
        chart.setDescription(chartDescription);
        chart.getLegend().setTextColor(Color.WHITE);
        chart.getLegend().setTextSize(14f);

    }

    private void populateScatterChart(ScatterChart chart, List<String[]> data, int columnIndex, float avgGwa,
                                      int color1, int color2, String dataSetLabel, String xDescription, String yDescription,
                                      String traitLabel1, String traitLabel2) {
        List<Entry> scatterEntries1 = new ArrayList<>();
        List<Entry> scatterEntries2 = new ArrayList<>();

        // Iterate through the dataset and add data points
        for (String[] row : data) {
            float traitValue1 = Float.parseFloat(row[columnIndex]);
            float traitValue2 = Float.parseFloat(row[columnIndex + 1]); // Assuming trait values are consecutive
            float gwa = Float.parseFloat(row[9]); // Assuming GWA is always at index 9

            // Add scatter plot data for both trait categories
            scatterEntries1.add(new Entry(traitValue1, gwa));
            scatterEntries2.add(new Entry(traitValue2, gwa));
        }


        // ScatterDataSet for trait category 1
        ScatterDataSet scatterDataSet1 = new ScatterDataSet(scatterEntries1, traitLabel1);
        scatterDataSet1.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        scatterDataSet1.setScatterShapeSize(8); // Set size of scatter points
        scatterDataSet1.setColor(color1); // Color for trait category 1
        scatterDataSet1.setDrawValues(false); // Hide values on the points

        // ScatterDataSet for trait category 2
        ScatterDataSet scatterDataSet2 = new ScatterDataSet(scatterEntries2, traitLabel2);
        scatterDataSet2.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        scatterDataSet2.setScatterShapeSize(8); // Set size of scatter points
        scatterDataSet2.setColor(color2); // Color for trait category 2
        scatterDataSet2.setDrawValues(false); // Hide values on the points

        // Clear existing data
        chart.clear();

        // Combine scatter data for both trait categories
        ScatterData scatterData = new ScatterData(scatterDataSet1, scatterDataSet2);

        // Set up the scatter chart
        chart.setData(scatterData);
        chart.invalidate();

        Description scatterDescription = new Description();
        scatterDescription.setText(xDescription + " vs " + yDescription + " vs GWA");
        scatterDescription.setTextColor(Color.WHITE); // Set text color to white
        scatterDescription.setTextSize(14f);
        chart.setDescription(scatterDescription);

        // Set x-axis and y-axis label colors to white
        chart.getXAxis().setTextColor(Color.WHITE);
        chart.getAxisLeft().setTextColor(Color.WHITE);
        chart.getAxisRight().setTextColor(Color.WHITE);
        chart.getLegend().setTextColor(Color.WHITE);
        chart.getLegend().setTextSize(14f);
    }

    // Method to get the column index for the current trait category
    private int getColumnIndex(String currentCategory) {
        switch (currentCategory) {
            case "introversion_extroversion":
                return 0;
            case "intuition_sensing":
                return 2;
            case "thinking_feeling":
                return 4;
            case "judging_perceiving":
                return 6;
            default:
                return -1;
        }
    }


    private List<String[]> loadCSVData(InputStream inputStream) {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] nextLine;
            boolean firstLine = true; // Add this flag
            while ((nextLine = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header row
                    continue;
                }
                data.add(nextLine);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return data;
    }



}
