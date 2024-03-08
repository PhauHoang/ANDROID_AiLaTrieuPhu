package com.example.ailatrieuphu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;




import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvQuestion;
    private TextView tvContenQuestion;
    private TextView tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4;
    private List<Question> MlistQuestion;
    private Question mQuestion;
    private int currenQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        MlistQuestion = getListQuestion();
        if (MlistQuestion.isEmpty()){
            return;
        }
        setDataQuestion(MlistQuestion.get(currenQuestion));
    }

    private void setDataQuestion(Question question) {
        if (question == null){
            return;

        }
        mQuestion = question;
        tvAnswer1.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer2.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer3.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer4.setBackgroundResource(R.drawable.bg_blue_corner_30);



        String titleQuestion = "Câu hỏi số " + (currenQuestion + 1);
        tvQuestion.setText(titleQuestion);

        // Hiển thị nội dung câu hỏi và đáp án theo vị trí đã xáo trộn
        tvContenQuestion.setText(question.getContent());
        tvAnswer1.setText(question.getListAnswer().get(0).getContent());
        tvAnswer2.setText(question.getListAnswer().get(1).getContent());
        tvAnswer3.setText(question.getListAnswer().get(2).getContent());
        tvAnswer4.setText(question.getListAnswer().get(3).getContent());

        tvAnswer1.setOnClickListener(this);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4.setOnClickListener(this);



    }

    private void initUi() {
        tvQuestion = findViewById(R.id.tv_question);
        tvContenQuestion = findViewById(R.id.tv_content_question);
        tvAnswer1 = findViewById(R.id.tv_answer1);
        tvAnswer2 = findViewById(R.id.tv_answer2);
        tvAnswer3 = findViewById(R.id.tv_answer3);
        tvAnswer4 = findViewById(R.id.tv_answer4);
    }
    private List<Question> getListQuestion(){
        List<Question> list = new ArrayList<>();

        List<Answer> answersList1 = new ArrayList<>();
        answersList1.add(new Answer("Thằn Lằn", false) );
        answersList1.add(new Answer("Cóc", true) );
        answersList1.add(new Answer("Ếch", false) );
        answersList1.add(new Answer("Nhái", false) );

        List<Answer> answersList2 = new ArrayList<>();
        answersList2.add(new Answer("Đỏ", false) );
        answersList2.add(new Answer("Đen", false) );
        answersList2.add(new Answer("Vàng", false) );
        answersList2.add(new Answer("Xanh", true) );

        List<Answer> answersList3 = new ArrayList<>();
        answersList3.add(new Answer("CÓ", true) );
        answersList3.add(new Answer("Không", false) );
        answersList3.add(new Answer("Bình Thường", false) );
        answersList3.add(new Answer("Không biết môn này là môn nào", false) );

        List<Answer> answersList4 = new ArrayList<>();
        answersList4.add(new Answer("Có, tôi là Triệu Phú", false) );
        answersList4.add(new Answer("Không, Tôi là Triệu Phú", true) );
        answersList4.add(new Answer("Không phải, tôi là triệu phú", false) );
        answersList4.add(new Answer("Đúng rồi, tôi là triệu phú", false) );

        List<Answer> answersList5 = new ArrayList<>();
        answersList5.add(new Answer("Chắc chắn rồi", true) );
        answersList5.add(new Answer("Dĩ nhiên rồi", false) );
        answersList5.add(new Answer("Chứ còn gì nữa", false) );
        answersList5.add(new Answer("Phải là như vậy", false) );



        list.add(new Question(1, " Đâu là tên một loại chợ?", answersList1));
        list.add(new Question(2, " Màu chủ đạo của tờ tiền Polymer mệnh giá 500.000 đồng là gì?", answersList2));
        list.add(new Question(3, " Môn lập trình Android có khó không?", answersList3));
        list.add(new Question(4, " Bạn Nghĩ bạn có thể là triệu phú không?", answersList4));
        list.add(new Question(5, " 10 Điểm Android", answersList5));

        return list;
    }


    @Override

    public void onClick(View v) {
        if (v.getId() == R.id.tv_answer1) {
            tvAnswer1.setBackgroundResource(R.drawable.bg_blue_corner_30);
            checkAnswer(tvAnswer1, mQuestion, mQuestion.getListAnswer().get(0));
        } else if (v.getId() == R.id.tv_answer2) {
            tvAnswer2.setBackgroundResource(R.drawable.bg_blue_corner_30);
            checkAnswer(tvAnswer2, mQuestion, mQuestion.getListAnswer().get(1));
        } else if (v.getId() == R.id.tv_answer3) {
            tvAnswer3.setBackgroundResource(R.drawable.bg_blue_corner_30);
            checkAnswer(tvAnswer3, mQuestion, mQuestion.getListAnswer().get(2));
        } else if (v.getId() == R.id.tv_answer4) {
            tvAnswer4.setBackgroundResource(R.drawable.bg_blue_corner_30);
            checkAnswer(tvAnswer4, mQuestion, mQuestion.getListAnswer().get(3));
        }
    }


    private void checkAnswer(TextView textView, Question question, Answer answer){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (answer.isCorrect()){
                    textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                    nextQuestion();
                }
                else{
                    textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                    showAnswerCorrect(question);
                    gameOver();
                }
            }

        },   1000);

    }



    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Bạn Thua Rồi");
                restartGame();

            }
        } , 1000);


    }
    private void restartGame() {
        shuffleQuestions();
        currenQuestion = 0;
        setDataQuestion(MlistQuestion.get(currenQuestion));
    }

    private void shuffleQuestions() {
        Collections.shuffle(MlistQuestion);
        for (Question question : MlistQuestion) {
            Collections.shuffle(question.getListAnswer());
        }
    }


    private void showAnswerCorrect(Question question) {
        if (question == null || question.getListAnswer() == null || question.getListAnswer().isEmpty()){
            return;
        }
        if (question.getListAnswer().get(0).isCorrect()){
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);
        }else if (question.getListAnswer().get(1).isCorrect()){
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);
    }
        else if (question.getListAnswer().get(2).isCorrect()){
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
        else if (question.getListAnswer().get(3).isCorrect()){
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);
        }}

    private void nextQuestion() {
        if (currenQuestion == MlistQuestion.size() -1){
            showDialog("Xin Chúc Mừng Bạn Là Triệu Phú");
        }else {
            currenQuestion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    setDataQuestion(MlistQuestion.get(currenQuestion));
                }
            } , 1000);


        }
    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currenQuestion = 0;
                setDataQuestion(MlistQuestion.get(currenQuestion));
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }}
