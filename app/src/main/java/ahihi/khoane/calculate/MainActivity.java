package ahihi.khoane.calculate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animationView;
    Spinner spinner;
    Toolbar toolbar;
    TextView tvPhamVi, tvTinhToan, tvDien1, tvDien2,
            tvChonDuLieu, tvSo1, tvPheptinh, tvSo2, tvSo3,
            tvCheckFinal, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8,
            tv9, tv0, tvCancel;
    LinearLayout tvOk;
    ScrollView scrollView;
    Random generator;
    int check = 0;
    int checkspiner = 0, spinertemp = 0;
    int result = 0;
    int big = 0;
    int small = 0;
    MediaPlayer mediaPlayerTrue,mediaPlayerFalse,click;
    List<Integer> soundListTrue;
    List<Integer> soundListFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        onClickChonBai();
        onClickSo();
        tvSo3.setText("?");
        soundListTrue = new ArrayList<Integer>();
//        mediaPlayerTrue = MediaPlayer.create(this,R.raw.truesoundeffect);
        soundListTrue.add(R.raw.truesoundeffect);
        soundListTrue.add(R.raw.begioiqua);
        soundListTrue.add(R.raw.dung);
        soundListTrue.add(R.raw.tuyetvoi);
        soundListTrue.add(R.raw.dungroibeoi);

        soundListFalse = new ArrayList<Integer>();
//        mediaPlayerTrue = MediaPlayer.create(this,R.raw.truesoundeffect);
        soundListFalse.add(R.raw.tiecquasai);
        soundListFalse.add(R.raw.oitiecqualainaobeoi);
        soundListFalse.add(R.raw.incorrectsoundeffect);
        soundListFalse.add(R.raw.sairoilamlainaobeoi);
    }

    private void playClick() {
        mediaPlayerTrue = MediaPlayer.create(this, R.raw.click);
        mediaPlayerTrue.start();
    }

    private void playTrue() {
        int randomInt = (new Random().nextInt(soundListTrue.size()));
        int sound = soundListTrue.get(randomInt);
        mediaPlayerTrue = MediaPlayer.create(this, sound);
        mediaPlayerTrue.start();
    }

    private void playFalse() {
        int randomInt = (new Random().nextInt(soundListFalse.size()));
        int sound = soundListFalse.get(randomInt);
        mediaPlayerFalse = MediaPlayer.create(this, sound);
        mediaPlayerFalse.start();
    }
    private void stopPlay() {
        if (mediaPlayerTrue != null){
            mediaPlayerTrue.release();
            mediaPlayerTrue = null;
        }
        if (mediaPlayerFalse != null){
            mediaPlayerFalse.release();
            mediaPlayerFalse = null;
        }
    }

    private void animationbig(){
        final float startSize = 25; // Size in pixels
        final float endSize = 40;
        long animationDuration = 600; // Animation duration in ms
        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tvCheckFinal.setTextSize(animatedValue);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationsmall();
            }
        });
        animator.start();
    }
    private void animationsmall(){
        final float startSize = 40; // Size in pixels
        final float endSize = 25;
        long animationDuration = 600; // Animation duration in ms
        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tvCheckFinal.setTextSize(animatedValue);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tvOk.setEnabled(true);
            }
        });
        animator.start();
    }

    private void onClickSo() {
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(View.FOCUS_DOWN);
                tvOk.setEnabled(false);
                if (tvSo1.getText().equals("?") || tvSo2.getText().equals("?") || tvSo3.getText().equals("?")
                        || tvSo1.getText().equals("0") || tvSo2.getText().equals("0") || tvSo3.getText().equals("0")) {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn số!", Toast.LENGTH_SHORT).show();
                    tvOk.setEnabled(true);
                } else {
                    int so1 = Integer.parseInt(tvSo1.getText().toString());
                    int so2 = Integer.parseInt(tvSo2.getText().toString());
                    int so3 = Integer.parseInt(tvSo3.getText().toString());
                    if (tvPheptinh.getText().toString().trim().equals("+")) {
                        if (so1 + so2 == so3) {
                            tvOk.setEnabled(false);
                            playTrue();
//                            animationView.setAnimation(R.raw.okdone);
//                            animationView.setVisibility(View.VISIBLE);
//                            mediaPlayerTrue.start();
                            tvCheckFinal.setTextColor(getResources().getColor(R.color.green));
                            tvCheckFinal.setText("Cộng chính xác!");
                            animationbig();
                            mediaPlayerTrue.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    checkspin();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvOk.setEnabled(true);
                                            tvCheckFinal.setText("Cộng chính xác!");
                                            animationView.setVisibility(View.GONE);
                                        }
                                    },1000);
                                }
                            });
                        } else {
                            playFalse();
                            tvCheckFinal.setTextColor(getResources().getColor(R.color.red));
                            tvCheckFinal.setText("Cộng sai rồi!");
                            animationbig();
                            mediaPlayerFalse.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvOk.setEnabled(true);
                                            animationView.setVisibility(View.GONE);
                                        }
                                    },1000);
                                }
                            });
                        }
                    } else {
                        if (so1 - so2 == so3) {
                            playTrue();
                            tvCheckFinal.setTextColor(getResources().getColor(R.color.green));
                            tvCheckFinal.setText("Trừ chính xác!");
                            animationbig();
                            mediaPlayerTrue.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    checkspin();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvOk.setEnabled(true);
                                            animationView.setVisibility(View.GONE);
                                        }
                                    },1000);
                                }
                            });
                        } else {
                            playFalse();
                            tvCheckFinal.setTextColor(getResources().getColor(R.color.red));
                            tvCheckFinal.setText("Trừ sai rồi!");
                            animationbig();
                            mediaPlayerFalse.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvOk.setEnabled(true);
                                            animationView.setVisibility(View.GONE);
                                        }
                                    },1000);
                                }
                            });
                        }
                    }
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    if (tvSo2.getText().toString().length() <= 1) {
                        tvSo2.setText("0");
                    } else {
                        String temp = String.valueOf(tvSo2.getText().toString().charAt(0));
                        tvSo2.setText(temp + "");
                    }
                } else if (check == 1) {
                    if (tvSo1.getText().toString().length() <= 1) {
                        tvSo1.setText("0");
                    } else {
                        String temp = String.valueOf(tvSo1.getText().toString().charAt(0));
                        tvSo1.setText(temp + "");
                    }
                } else {
                    if (tvSo3.getText().toString().length() <= 1) {
                        tvSo3.setText("0");
                    } else {
                        String temp = String.valueOf(tvSo3.getText().toString().charAt(0));
                        tvSo3.setText(temp + "");
                    }
                }
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 1);
                } else if (check == 1) {
                    check2so(tvSo1, 1);
                } else {
                    check2so(tvSo3, 1);
                }
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 2);
                } else if (check == 1) {
                    check2so(tvSo1, 2);
                } else {
                    check2so(tvSo3, 2);
                }
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 3);
                } else if (check == 1) {
                    check2so(tvSo1, 3);
                } else {
                    check2so(tvSo3, 3);
                }
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 4);
                } else if (check == 1) {
                    check2so(tvSo1, 4);
                } else {
                    check2so(tvSo3, 4);
                }
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 5);
                } else if (check == 1) {
                    check2so(tvSo1, 5);
                } else {
                    check2so(tvSo3, 5);
                }
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 6);
                } else if (check == 1) {
                    check2so(tvSo1, 6);
                } else {
                    check2so(tvSo3, 6);
                }
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 7);
                } else if (check == 1) {
                    check2so(tvSo1, 7);
                } else {
                    check2so(tvSo3, 7);
                }
            }
        });
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 8);
                } else if (check == 1) {
                    check2so(tvSo1, 8);
                } else {
                    check2so(tvSo3, 8);
                }
            }
        });
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 9);
                } else if (check == 1) {
                    check2so(tvSo1, 9);
                } else {
                    check2so(tvSo3, 9);
                }
            }
        });
        tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
                if (check == 2) {
                    check2so(tvSo2, 0);
                } else if (check == 1) {
                    check2so(tvSo1, 0);
                } else {
                    check2so(tvSo3, 0);
                }
            }
        });
    }

    private void check2so(TextView textView, int number) {
        if (textView.getText().toString().equals("?") || textView.getText().toString().equals("0")) {
            textView.setText("" + number);
        } else {
            String temp = textView.getText().toString() + number;
            Log.d("mmm", "onClick: " + temp);
            if (temp.length() >= 3) {
                Toast.makeText(MainActivity.this, "Tối đa 2 số!", Toast.LENGTH_SHORT).show();
            } else {
                textView.setText(temp);
            }
        }
    }

    private void add(int i) {
        if (i == 2) {
            tvSo1.setText("" + big);
            tvSo3.setText("" + result);
        } else if (i == 1) {
            tvSo2.setText("" + small);
            tvSo3.setText("" + result);
        } else {
            tvSo1.setText("" + big);
            tvSo2.setText("" + small);
        }
    }
    private void addtru(int i) {
        if (i == 2) {
            tvSo1.setText("" + result);
            tvSo3.setText("" + big);
        } else if (i == 1) {
            tvSo2.setText("" + small);
            tvSo3.setText("" + big);
        } else {
            tvSo1.setText("" + result);
            tvSo2.setText("" + small);
        }
    }

    private void randomtronchuc() {
        int a = generator.nextInt(100);
        int result1 = generator.nextInt(10 - 2) + 2;
        result = Integer.parseInt(result1 + "0");
        int so1 = generator.nextInt(result1 - 1) + 1;
        so1 = Integer.parseInt(so1 + "0");
        if (result - so1 < so1) {
            big = so1;
            small = result - big;
        } else {
            small = so1;
            big = result - small;
        }
    }

    private void randomKN() {
        result = generator.nextInt(100 - 2) + 2;
        int so1 = generator.nextInt(result - 1) + 1;
        if (result < 10) {
            small = result - so1;
        } else {
            int tempsonho = 0;
            int rs1 = Integer.parseInt(String.valueOf(String.valueOf(result).charAt(0)));
            int rs2 = Integer.parseInt(String.valueOf(String.valueOf(result).charAt(1)));
            if (rs2 < 2) {
                rs2 = generator.nextInt(10 - 2) + 2;
            }
            int s1 = so1;
            int s2 = 0;
            if (s1 > 9) {
                s1 = Integer.parseInt(String.valueOf(String.valueOf(so1).charAt(0)));
                s2 = Integer.parseInt(String.valueOf(String.valueOf(so1).charAt(1)));
                if (s2 == 0) {
                    s2 = generator.nextInt(rs2 - 1) + 1;
                    tempsonho = s2;
                } else {
                    if (rs2 < s2) {
                        s2 = generator.nextInt(rs2 - 1) + 1;
                        tempsonho = s2;
                    } else {
                        tempsonho = Integer.parseInt(s1 + "" + s2);
                    }
                }
            } else {
                s1 = generator.nextInt(rs2 - 1) + 1;
                tempsonho = s1;
            }
            result = Integer.parseInt(rs1 + "" + rs2);
            if (result - tempsonho < tempsonho) {
                big = tempsonho;
                small = result - tempsonho;
            } else {
                small = tempsonho;
                big = result - tempsonho;
            }
        }
    }

    private void randomTD() {
        result = generator.nextInt(100 - 2) + 2;
        int so1 = generator.nextInt(result - 1) + 1;

        if (result - so1 < so1) {
            big = so1;
            small = result - so1;
        } else {
            small = so1;
            big = result - so1;
        }
    }

    private void checkChangeMang() {
        int i = check;
        if (i == 2) {
            tvSo1.setBackgroundResource(0);
            tvSo1.setTextColor(getResources().getColor(R.color.orin));
            tvSo1.setText("0");

            tvSo2.setBackgroundResource(R.drawable.btn_number1_ok);
            tvSo2.setTextColor(getResources().getColor(R.color.text));
            tvSo2.setText("?");

            tvSo3.setBackgroundResource(0);
            tvSo3.setTextColor(getResources().getColor(R.color.orin));
            tvSo3.setText("0");
        } else if (i == 1) {
            tvSo1.setBackgroundResource(R.drawable.btn_number1_ok);
            tvSo1.setTextColor(getResources().getColor(R.color.text));
            tvSo1.setText("?");

            tvSo2.setBackgroundResource(0);
            tvSo2.setTextColor(getResources().getColor(R.color.orin));
            tvSo2.setText("0");

            tvSo3.setBackgroundResource(0);
            tvSo3.setTextColor(getResources().getColor(R.color.orin));
            tvSo3.setTextColor(getResources().getColor(R.color.orin));
            tvSo3.setText("0");
        } else {
            tvSo1.setBackgroundResource(0);
            tvSo1.setTextColor(getResources().getColor(R.color.orin));
            tvSo1.setText("0");

            tvSo2.setBackgroundResource(0);
            tvSo2.setTextColor(getResources().getColor(R.color.orin));
            tvSo2.setText("0");

            tvSo3.setBackgroundResource(R.drawable.btn_number1_ok);
            tvSo3.setTextColor(getResources().getColor(R.color.text));
            tvSo3.setText("?");
        }
    }

    private void hoanvi(){
        if (generator.nextBoolean()) {
            int TrungGian = big;
            big = small;
            small = TrungGian;
        }
    }
    private void hoanvitru(){
        if (generator.nextBoolean()) {
            int TrungGian = big;
            big = small;
            small = TrungGian;
        }
    }

    private void checkspin() {
        checkChangeMang();
        if (checkspiner == 0) {
            randomKN();
            tvPheptinh.setText("+");
            hoanvi();
            add(check);
        } else if (checkspiner == 1) {
            randomtronchuc();
            tvPheptinh.setText("+");
            hoanvi();
            add(check);
        } else if (checkspiner == 2) {
            randomTD();
            tvPheptinh.setText("+");
            hoanvi();
            add(check);
        } else if (checkspiner == 3) {
            randomKN();
            tvPheptinh.setText("-");
            hoanvitru();
            addtru(check);
        } else if (checkspiner == 4) {
            randomtronchuc();
            tvPheptinh.setText("-");
            hoanvitru();
            addtru(check);
        } else {
            randomTD();
            tvPheptinh.setText("-");
            hoanvitru();
            addtru(check);
        }
    }
    private void onClickChonBai() {
        tvChonDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkspiner = spinertemp;
                tvCheckFinal.setTextColor(getResources().getColor(R.color.text));
                tvCheckFinal.setText(getResources().getText(R.string.result_text));
                if (check == 2) {
                    checkChangeMang();
                    checkspin();
                } else if (check == 1) {
                    checkChangeMang();
                    checkspin();
                } else {
                    checkChangeMang();
                    checkspin();
                }
            }
        });
        tvDien2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 2;
                checkChangeBackground(2);
                checkChangeMang();
            }
        });
        tvDien1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 1;
                checkChangeBackground(1);
                checkChangeMang();
            }
        });
        tvTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 0;
                checkChangeBackground(0);
                checkChangeMang();
            }
        });
    }

    private void checkChangeBackground(int a) {
        if (2 == a) {
            tvDien2.setBackgroundResource(R.drawable.box_purple_custom_right);
            tvDien2.setTextColor(Color.parseColor("#ffffff"));

            tvDien1.setBackgroundColor(getResources().getColor(R.color.text));
            tvDien1.setTextColor(getResources().getColor(R.color.blue));

            tvTinhToan.setBackgroundResource(R.drawable.box_while_custom_left);
            tvTinhToan.setTextColor(getResources().getColor(R.color.blue));
        } else if (1 == a) {
            tvDien2.setBackgroundResource(R.drawable.box_while_custom_right);
            tvDien2.setTextColor(getResources().getColor(R.color.blue));

            tvDien1.setBackgroundColor(getResources().getColor(R.color.blue));
            tvDien1.setTextColor(getResources().getColor(R.color.text));

            tvTinhToan.setBackgroundResource(R.drawable.box_while_custom_left);
            tvTinhToan.setTextColor(getResources().getColor(R.color.blue));

        } else {

            tvDien2.setBackgroundResource(R.drawable.box_while_custom_right);
            tvDien2.setTextColor(getResources().getColor(R.color.blue));

            tvDien1.setBackgroundColor(getResources().getColor(R.color.text));
            tvDien1.setTextColor(getResources().getColor(R.color.blue));

            tvTinhToan.setBackgroundResource(R.drawable.box_purple_custom_left);
            tvTinhToan.setTextColor(getResources().getColor(R.color.text));

        }
    }

    private void initWidget() {
        animationView = findViewById(R.id.animation_view);
        animationView.setVisibility(View.GONE);
        spinner = findViewById(R.id.spinerPhepTinh);
        tvPhamVi = findViewById(R.id.tvPhamVi);
        tvTinhToan = findViewById(R.id.tvcbTinhToan);
        tvDien1 = findViewById(R.id.tvcbDien1);
        tvDien2 = findViewById(R.id.tvcbDien2);
        tvChonDuLieu = findViewById(R.id.tvChonDuLieu);
        tvSo1 = findViewById(R.id.tvSoThu1);
        tvPheptinh = findViewById(R.id.tvPhepTinh);
        tvSo2 = findViewById(R.id.tvSoThu2);
        tvSo3 = findViewById(R.id.tvSoThu3);
        tvCheckFinal = findViewById(R.id.tvCheckFinal);
        tv1 = findViewById(R.id.btnso1);
        tv2 = findViewById(R.id.btnso2);
        tv3 = findViewById(R.id.btnso3);
        tv4 = findViewById(R.id.btnso4);
        tv5 = findViewById(R.id.btnso5);
        tv6 = findViewById(R.id.btnso6);
        tv7 = findViewById(R.id.btnso7);
        tv8 = findViewById(R.id.btnso8);
        tv9 = findViewById(R.id.btnso9);
        tv0 = findViewById(R.id.btnso0);
        tvOk = findViewById(R.id.btnOk);
        tvCancel = findViewById(R.id.btnC);
        scrollView = findViewById(R.id.scrollview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_navigate_before_black_40dp);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setHomeAsUpIndicator(drawable);
        generator = new Random();
        List<String> categories = new ArrayList<String>();
        categories.add("Cộng không nhớ");
        categories.add("Cộng tròn chục");
        categories.add("Cộng tự do");
        categories.add("Trừ không mượn");
        categories.add("Trừ tròn chục");
        categories.add("Trừ tự do");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);//ndroid.R.layout.simple_spinner_item
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String state = adapterView.getItemAtPosition(i).toString();
                spinertemp = i;
                if(i<=2){
                    tvPheptinh.setText("+");
                } else {
                    tvPheptinh.setText("-");
                }
                checkChangeMang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
