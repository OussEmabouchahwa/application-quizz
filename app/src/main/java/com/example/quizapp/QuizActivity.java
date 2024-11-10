package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup optionsGroup;
    private Button nextButton;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);

        // Charger les questions
        loadQuestions();
        showQuestion();

        nextButton.setOnClickListener(v -> checkAnswer());
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of France?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), 0));
        questionList.add(new Question("What is 2 + 2?",
                Arrays.asList("3", "4", "5", "6"), 1));
        questionList.add(new Question("What is the capital of Spain?",
                Arrays.asList("Paris", "Madrid", "Rome", "Lisbon"), 1));
    }

    private void showQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestionText());
            optionsGroup.clearCheck();

            for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                ((RadioButton) optionsGroup.getChildAt(i)).setText(currentQuestion.getOptions().get(i));
            }
        } else {
            Toast.makeText(this, "Quiz finished! Your score: " + score, Toast.LENGTH_LONG).show();
            finish(); // Terminer l'activité après le quiz
        }
    }

    private void checkAnswer() {
        int selectedOptionIndex = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId()));

        if (selectedOptionIndex == questionList.get(currentQuestionIndex).getCorrectAnswerIndex()) {
            score++;
        }

        currentQuestionIndex++;
        showQuestion();
    }
}
