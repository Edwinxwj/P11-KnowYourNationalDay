package sg.edu.rp.c346.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> listText;
    ArrayAdapter<String> aa;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);

        listText = new ArrayList<String>();
        listText.add("Singapore Nation Day is on 9 Aug");
        listText.add("Singapore is 52 years old");
        listText.add("Theme is '#OneNationTogether");

        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listText);
        lv.setAdapter(aa);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.passphrase, null);
        final EditText etPassphrase = (EditText) passPhrase.findViewById(R.id.editTextPassPhrase);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Login")
                .setView(passPhrase)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (etPassphrase.getText().toString().equals("650193")) {
                            //This is to get the text from the Passphrase and display a toast message.
                            Toast.makeText(MainActivity.this, "You had entered " +
                                    etPassphrase.getText().toString(), Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(MainActivity.this,"You have enter the wrong access code",Toast.LENGTH_LONG).show();
                            finish();

                        }
                    }
                });
        builder.setNegativeButton("NO ACCESS CODE",null);
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            String[] list = new String[]{"Email", "SMS"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(list, new DialogInterface.OnClickListener() {
                        //The parameter "which" is the item index
                        //clicked. started from 0
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i = 1; i < listText.size(); i++) {
                                message += i + listText.get(i) + "\n";
                            }
                            if (which == 0) {
                                // The action you want this intent to do;
                                // ACTION_SEND is used to indicate sending text
                                Intent email = new Intent(Intent.ACTION_SEND);
                                // Put essentials like email address, subject & body text
                                email.putExtra(Intent.EXTRA_EMAIL,
                                        new String[]{"edwin@rp.edu.sg"});
                                email.putExtra(Intent.EXTRA_SUBJECT,
                                        "National Day");
                                email.putExtra(Intent.EXTRA_TEXT,
                                        message);
                                // This MIME type indicates email
                                email.setType("message/rfc822");
                                // createChooser shows user a list of app that can handle
                                // this MIME type, which is, email
                                startActivity(Intent.createChooser(email,
                                        "Choose an Email client :"));

                            } else if (which == 1) {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage("5556", null, message, null, null);
                                Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else if (item.getItemId() == R.id.action_quiz){
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout passPhrase =
                    (LinearLayout) inflater.inflate(R.layout.quiz, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Test Yourself!")
                    .setView(passPhrase)
                    .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            RadioGroup rg1 = (RadioGroup)passPhrase.findViewById(R. id. rg1);
                            RadioGroup rg2 = (RadioGroup)passPhrase.findViewById(R. id. rg2);
                            RadioGroup rg3 = (RadioGroup)passPhrase.findViewById(R. id. rg3);

                            int first = rg1.getCheckedRadioButtonId();
                            int second = rg2.getCheckedRadioButtonId();
                            int third = rg3.getCheckedRadioButtonId();

                            RadioButton rb1 = (RadioButton)passPhrase.findViewById(first);
                            RadioButton rb2 = (RadioButton)passPhrase.findViewById(second);
                            RadioButton rb3 = (RadioButton)passPhrase.findViewById(third);
                            int score = 0;
                            if (rb1.getText().toString().equalsIgnoreCase("No")) {
                                score += 1;
                            } else {
                            }
                            if (rb2.getText().toString().equalsIgnoreCase("Yes")) {
                                score += 1;
                            } else {
                            }
                            if (rb3.getText().toString().equalsIgnoreCase("Yes")) {
                                score += 1;
                            } else {
                            }
                            Toast.makeText(MainActivity.this, "Score " + score,
                                    Toast.LENGTH_LONG).show();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);



        } else if (item.getItemId() == R.id.action_quit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure")
                    //Set text for the positive button and the corresponding
                    //On clickListener when it is clicked
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "You click yes", Toast.LENGTH_LONG).show();

                        }
                    })
                    //Set text for the negative button and the corresponding
                    // onClickListener when it is clicked
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "You clicked no to quit", Toast.LENGTH_LONG).show();
                        }
                    });

            //Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            finish();
        }

            return super.onOptionsItemSelected(item);
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
