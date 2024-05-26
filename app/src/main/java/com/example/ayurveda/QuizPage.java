package com.example.ayurveda;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class QuizPage extends AppCompatActivity {

    private TextView questionCounter;
    private ImageView questionImage;
    private TextView questionText;
    private LinearLayout answerContainer;
    private Button next_btn;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        questionCounter = findViewById(R.id.question_counter);
        questionImage = findViewById(R.id.image_quiz);
        questionText = findViewById(R.id.question);
        answerContainer = findViewById(R.id.answer_container);
        next_btn = findViewById(R.id.next_btn);

        loadQuestions();
        showQuestion(currentQuestionIndex);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex++;

                if (currentQuestionIndex < questions.size()) {
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    showQuestion(currentQuestionIndex);

                } else {
                    showResult();
                }
            }
        });
    }
    private void loadQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question(R.drawable.star, "Guess the painting", new String[]{"Slavery! Slavery! by Kara Walker", "The Persistence of Memory by Salvador Dalí", "The Starry Night by Vincent Van Gogh", "Nocturne in Black and Gold, the Falling Rocket by James McNeill Whistler"}, 2));
        questions.add(new Question(R.drawable.animal, "Name the Animal shown", new String[]{"wallaby", "platypus", "echidna", "aardvark"}, 1));
        questions.add(new Question(R.drawable.brown, "Name that color ", new String[]{"magenta", "mellow yellow", "amaranth", "ocher brown"}, 3));
        questions.add(new Question(R.drawable.paris, "Where on earth is that?", new String[]{"Moscow", "Paris", "Rome", "Madrid"}, 1));
        questions.add(new Question(R.drawable.leo, "Name the zodiac sign", new String[]{"Leo", "Aries", "Capricorn", "Gemini"}, 0));
    }

    private void showQuestion(int questionIndex) {
        Question question = questions.get(questionIndex);
        questionCounter.setText("Question " + (questionIndex + 1) + "/" + questions.size());
        questionImage.setImageResource(question.getImageResId());
        questionText.setText(question.getQuestionText());

        answerContainer.removeAllViews();
        String[] answers = question.getAnswers();
        for (int i = 0; i < answers.length; i++) {
            Button button = new Button(this);
            button.setText(answers[i]);
            button.setBackgroundResource(R.drawable.btn_options);
            button.setTextColor(getResources().getColor(R.color.next_option_btn));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 20); // Left, Top, Right, Bottom margins
            button.setLayoutParams(params);


            int finalI = i;
            button.setOnClickListener(view -> {
                checkAnswer(finalI, question.getCorrectAnswerIndex(), button);
                disableButtons();
            });
            answerContainer.addView(button);

        }
    }

    private void checkAnswer(int selectedAnswerIndex, int correctAnswerIndex, Button selectedButton) {
        if (selectedAnswerIndex == correctAnswerIndex) {
            score++;
        }
        selectedButton.setBackgroundColor(getResources().getColor(R.color.selected));
    }

    private void disableButtons() {
        for (int i = 0; i < answerContainer.getChildCount(); i++) {
            answerContainer.getChildAt(i).setEnabled(false);

        }
    }
    private void showResult() {
        Intent intent = new Intent(QuizPage.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.size());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
        finish();
    }
}