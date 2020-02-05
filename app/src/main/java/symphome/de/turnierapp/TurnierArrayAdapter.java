package symphome.de.turnierapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Reck on 10.05.2017.
 */

public class TurnierArrayAdapter extends ArrayAdapter<Turnier> {

    private final Context context;
    private final List<Turnier> values;
    private int layout_id;

    public TurnierArrayAdapter( Context context, int resource, List<Turnier> values) {
        super(context, resource, values);
        this.layout_id = resource;
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id,	parent, false);
        TextView textView = (TextView)rowView.findViewById(R.id.tv_turnier);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.img_turnier);

        if (textView != null)
            textView.setText(values.get(position).getDescription());
        if (imageView != null) {
            if (values.get(position).getImageUri() == null ||
                values.get(position).getImageUri().trim().length()==0)
                imageView.setImageResource(R.drawable.ic_turnier);
            else
                setImageBitmap(imageView, values.get(position).getImageUri());
        }
        return rowView;
    }

    public void setImageBitmap(ImageView ivTurnier, String imageString) {
        // Skalieren des Bildes in eine Bitmap der Größe 96X96
        Bitmap b1 = BitmapFactory.decodeFile(imageString);
        if (b1 != null) {
            float w1 = b1.getWidth();
            float h1 = b1.getHeight();
            int h11 = (int) h1 > 96 ? 96 : (int) h1;
            int w11 = (int) (w1 / h1 * (float) h11);
            Bitmap b11 = Bitmap.createScaledBitmap(b1, w11, h11, false);
            ivTurnier.setImageBitmap(b11);
        }
    }
}

