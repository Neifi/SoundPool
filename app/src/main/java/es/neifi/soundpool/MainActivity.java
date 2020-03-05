package es.neifi.soundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SoundPool sp;
    private int idSong1,idSong2;
    private Button playSong1,playSong2,stop;
    private SeekBar volume;
    private int soundPoolId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPoolId = 0;
        playSong1 = findViewById(R.id.song1);
        playSong2 = findViewById(R.id.song2);
        stop = findViewById(R.id.stop);
        volume = findViewById(R.id.seekBar);

        //init soundpool

        //Check android version
      /*  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sp = new SoundPool.Builder().setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            }

        }*/
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);

        //Load file
        idSong1 = sp.load(this,R.raw.song1,0);
        idSong2 = sp.load(this,R.raw.song2,0);

        volume.setMin(1);
        volume.setMax(100);


        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               sp.setVolume(soundPoolId,(float)progress/100,(float)progress/100);
                
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playSong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               soundPoolId = sp.play(idSong1,1,1,1,1,1);

            }
        });

        playSong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               soundPoolId = sp.play(idSong2,1,1,1,1,1);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp.stop(soundPoolId);

            }
        });
    }
}
