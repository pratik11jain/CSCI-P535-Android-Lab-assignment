package com.iub.pervasivecomputing.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Question> questions_A, questions_B;
    private Question.CATEGORY MAIN_CATEGORY;
    private int score_A, score_B, index_A, index_B, NO_OF_A, NO_OF_B, remaining_count;

    private void init() {
        this.MAIN_CATEGORY = Question.CATEGORY.A;
        this.questions_A = new ArrayList<>();
        this.questions_B = new ArrayList<>();
        this.addAllQuestions();
        this.score_A = 0;
        this.score_B = 0;
        this.index_A = 0;
        this.index_B = 0;
        this.NO_OF_A = this.questions_A.size();
        this.NO_OF_B = this.questions_B.size();
        this.remaining_count = NO_OF_A + NO_OF_B;
    }

    void checkScore() {
        if (this.score_A < 0)
            this.score_A = 0;
        if (this.score_B < 0)
            this.score_B = 0;
    }

    private void addAllQuestions() {
        final Question q1 = new Question(1, "qa1", "1a", "1b", "1c", "1d", Question.Answer.A, false);
        final Question q2 = new Question(2, "qa2", "2a", "2b", "2c", "2d", Question.Answer.B, false);
        final Question q3 = new Question(3, "qa3", "3a", "3b", "3c", "3d", Question.Answer.C, false);
        final Question q4 = new Question(4, "qa4", "4a", "4b", "4c", "4d", Question.Answer.D, false);
        final Question q5 = new Question(5, "qa5", "5a", "5b", "5c", "5d", Question.Answer.A, false);
        final Question q6 = new Question(6, "qa6", "6a", "6b", "6c", "6d", Question.Answer.B, false);
        this.questions_A.addAll(Arrays.asList(q1, q2, q3, q4, q5, q6));
        final Question q7 = new Question(7, "qb1", "1a", "1b", "1c", "1d", Question.Answer.C, false);
        final Question q8 = new Question(8, "qb2", "2a", "2b", "2c", "2d", Question.Answer.D, false);
        final Question q9 = new Question(9, "qb3", "3a", "3b", "3c", "3d", Question.Answer.A, false);
        final Question q10 = new Question(10, "qb4", "4a", "4b", "4c", "4d", Question.Answer.D, false);
        final Question q11 = new Question(11, "qb5", "5a", "5b", "5c", "5d", Question.Answer.C, false);
        final Question q12 = new Question(12, "qb6", "6a", "6b", "6c", "6d", Question.Answer.D, false);
        this.questions_B.addAll(Arrays.asList(q7, q8, q9, q10, q11, q12));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        changeQuestion();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            init();
            changeQuestion();
        }

        return super.onOptionsItemSelected(item);
    }

    void inc_index() {
        if (MAIN_CATEGORY == Question.CATEGORY.A) {
            index_A++;
            if (index_A == NO_OF_A)
                index_A = 0;
        } else {
            index_B++;
            if (index_B == NO_OF_B)
                index_B = 0;
        }
    }

    void dec_index() {
        if (MAIN_CATEGORY == Question.CATEGORY.A) {
            index_A--;
            if (index_A < 0)
                index_A = NO_OF_A - 1;
        } else {
            index_B--;
            if (index_B < 0)
                index_B = NO_OF_B - 1;
        }
    }

    void changeQuestion() {
        Question q;
        int s;
        String category;
        checkScore();
        if (MAIN_CATEGORY == Question.CATEGORY.A) {
            q = questions_A.get(index_A);
            s = score_A;
            category = "A";
        } else {
            q = questions_B.get(index_B);
            s = score_B;
            category = "B";
        }
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(q.getStatement());
        RadioButton ra = (RadioButton) findViewById(R.id.radioButtonA);
        ra.setText(q.getA());
        RadioButton rb = (RadioButton) findViewById(R.id.radioButtonB);
        rb.setText(q.getB());
        RadioButton rc = (RadioButton) findViewById(R.id.radioButtonC);
        rc.setText(q.getC());
        RadioButton rd = (RadioButton) findViewById(R.id.radioButtonD);
        rd.setText(q.getD());
        Button submit = (Button) findViewById(R.id.Submit);
        boolean solved = q.isSolved();
        ra.setEnabled(!solved);
        rb.setEnabled(!solved);
        rc.setEnabled(!solved);
        rd.setEnabled(!solved);
        submit.setEnabled(!solved);
        TextView score = (TextView) findViewById(R.id.score);
        score.setText("Score (" + category + "): ");
        TextView scoreValue = (TextView) findViewById(R.id.scoreValue);
        scoreValue.setText("" + s);
        if (remaining_count <= 0) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Quiz Completed")
                    .setMessage("The Quiz will now Restart\nScore in Category A: " + score_A + "\nScore in Category B: " + score_B)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            init();
                            changeQuestion();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks her
        // e.
        int id = item.getItemId();

        if (id == R.id.cat_a) {
            Toast.makeText(this, "Category A", Toast.LENGTH_SHORT).show();
            MAIN_CATEGORY = Question.CATEGORY.A;
            changeQuestion();
        } else if (id == R.id.cat_b) {
            Toast.makeText(this, "Category B", Toast.LENGTH_SHORT).show();
            MAIN_CATEGORY = Question.CATEGORY.B;
            changeQuestion();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickNext(View view) {
        inc_index();
        changeQuestion();
    }

    public void onClickPrev(View view) {
        dec_index();
        changeQuestion();
    }

    public void onClickSubmit(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        if (group.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select at least one option", Toast.LENGTH_SHORT).show();
            return;
        }
        String message = "";
        if (MAIN_CATEGORY == Question.CATEGORY.A) {
            Question q = questions_A.get(index_A);
            q.setSolved(true);
            if (isCorrectAnswer(q)) {
                score_A += 2;
            } else {
                score_A--;
                message = "in";
            }
            questions_A.set(index_A, q);
        } else {
            Question q = questions_B.get(index_B);
            q.setSolved(true);
            if (isCorrectAnswer(q)) {
                score_B += 2;
            } else {
                score_B--;
                message = "in";
            }
            questions_B.set(index_B, q);
        }
        Toast.makeText(this, "Your Answer is " + message + "correct!", Toast.LENGTH_SHORT).show();
        remaining_count--;
        onClickNext(view);
    }

    private boolean isCorrectAnswer(Question q) {
        int checkedID = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        Question.Answer checked;
        if (checkedID == R.id.radioButtonA)
            checked = Question.Answer.A;
        else if (checkedID == R.id.radioButtonB)
            checked = Question.Answer.B;
        else if (checkedID == R.id.radioButtonC)
            checked = Question.Answer.C;
        else checked = Question.Answer.D;
        return checked == q.getAnswer();
    }
}
