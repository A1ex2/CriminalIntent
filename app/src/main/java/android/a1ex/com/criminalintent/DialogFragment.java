package android.a1ex.com.criminalintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

public class DialogFragment extends AppCompatActivity {

    private Crime mCrime;
    private File mPhotoFile;
    private ImageView mPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialog);

        Intent i = getIntent();
        UUID crimeId = (UUID) i.getSerializableExtra("crimeId");
        mCrime = CrimeLab.get(this).getCrime(crimeId);
        mPhotoFile = CrimeLab.get(this).getPhotoFile(mCrime);

        mPhotoView = (ImageView) findViewById(R.id.crime_photo);

        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), this);
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}
