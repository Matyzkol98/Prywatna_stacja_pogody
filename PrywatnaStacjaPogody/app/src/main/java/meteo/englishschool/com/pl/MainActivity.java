package meteo.englishschool.com.pl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private TextView textView_nazwa_stacji;
    private TextView textView_miejscowosc;
    private TextView textView_temperatura;
    private TextView textView_trend_temp;
    private TextView textView_wilgotnosc;
    private TextView textView_punkt_rosy;
    private TextView textView_cisnienie;
    private TextView textView_trend_cisn;
    private TextView textView_sila_wiatru;
    private TextView textView_pr_wiatru;
    private TextView textView_kier_wiatru;
    private TextView textView_wschod_sl;
    private TextView textView_zachod_sl;
    private TextView textView_dlugosc_doby;
    private TextView textView_prognoza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_miejscowosc = findViewById(R.id.textView_miejscowosc);
        textView_nazwa_stacji = findViewById(R.id.textView_nazwa_stacji);
        textView_temperatura = findViewById(R.id.textView_temperatura);
        textView_trend_temp = findViewById(R.id.textView_trend_temp);
        textView_wilgotnosc = findViewById(R.id.textView_wilgotność);
        textView_punkt_rosy = findViewById(R.id.textView_punkt_rosy);
        textView_cisnienie = findViewById(R.id.textView_cisnienie);
        textView_trend_cisn = findViewById(R.id.textView_trend_cisn);
        textView_sila_wiatru = findViewById(R.id.textView_sila_wiatru);
        textView_pr_wiatru = findViewById(R.id.textView_pr_wiatru);
        textView_kier_wiatru = findViewById(R.id.textView_kier_wiatru);
        textView_wschod_sl = findViewById(R.id.textView_wschod_sl);
        textView_zachod_sl = findViewById(R.id.textView_zachod_sl);
        textView_dlugosc_doby = findViewById(R.id.textView_dlugosc_doby);
        textView_prognoza = findViewById(R.id.textView_prognoza);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cached:
                Toast.makeText(this, "Wybrano opcję odświżania ...", Toast.LENGTH_SHORT).show();
                nazwaStacji();
                miejscowosc();
                temperatura();
                trend_temp();
                wilgotnosc();
                punkt_rosy();
                cisnienie();
                trend_cisn();
                sila_wiatru();
                pr_wiatru();
                kier_wiatru();
                wschod_sl();
                zachod_sl();
                dl_doby();
                prognoza();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Wybrano opcję ustawienia ...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,SettingsActivity.class));
//                startActivity(new Intent(this,O_programie.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // -------------- Poszczególne pola ------------------------------------------------------
    private void nazwaStacji() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select("h3").attr("h3")).append(doc.select("h3").text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_nazwa_stacji.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void miejscowosc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select("h1").attr("h1")).append(doc.select("h1").text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_miejscowosc.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void temperatura() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select(".p_DUZE").get(3).text());
                    builder.append(doc.select(".p_jedn").get(3).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_temperatura.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void trend_temp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append("Tendencja: ").append(doc.select(".p_red").get(3).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_trend_temp.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void wilgotnosc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select(".p_DUZE").get(2).text());
                    builder.append(doc.select(".p_jedn").get(2).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_wilgotnosc.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void punkt_rosy() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append("Punkt rosy: ").append(doc.select(".p_red").get(2).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_punkt_rosy.setText(builder.toString());
                    }
                });
            }
        }).start();
    }


    private void cisnienie() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select(".p_DUZE").get(1).text()).append(" ");
                    builder.append(doc.select(".p_jedn").get(1).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_cisnienie.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void trend_cisn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append("Tendencja: ").append(doc.select(".p_red").get(1).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_trend_cisn.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void sila_wiatru() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select(".p_DUZE").get(0).text()).append("\n");
                    builder.append(doc.select(".p_jedn").get(0).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_sila_wiatru.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void pr_wiatru() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select(".wieksze").get(0).text()).append("\n").append("m/s");
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_pr_wiatru.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void kier_wiatru() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select(".wieksze").get(1).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_kier_wiatru.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void wschod_sl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select("strong").get(0).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_wschod_sl.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void zachod_sl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/inde_x.htm").get();
                    builder.append(doc.select("strong").get(1).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_zachod_sl.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void dl_doby() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/inde_x.htm").get();
                    builder.append(doc.select("strong").get(2).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_dlugosc_doby.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    private void prognoza() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.meteo.englishschool.com.pl/index.htm").get();
                    builder.append(doc.select(".p_prognoza").get(0).text()).append("\n");
                    builder.append(doc.select("span").get(19).text());
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append(("\n"));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_prognoza.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

}