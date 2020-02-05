package symphome.de.turnierapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class ImageArrayAdapter extends ArrayAdapter<String[]> {

    private final Context context;
    private final List<String[]> values;
    private int layout_id;

    public ImageArrayAdapter(Context context, int resource,
                             List<String[]> values) {
        super(context, resource, values);
        this.layout_id = resource;
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        ImageView img1 = rowView.findViewById(R.id.img1);
        initImageView(img1, values.get(position)[0]);

        ImageView img2 = rowView.findViewById(R.id.img2);
        initImageView(img2, values.get(position)[1]);

        ImageView img3 = rowView.findViewById(R.id.img3);
        initImageView(img3, values.get(position)[2]);

        ImageView img4 = rowView.findViewById(R.id.img4);
        initImageView(img4, values.get(position)[3]);

        return rowView;
    }

    private void initImageView(ImageView img, String imageString) {
        Bitmap b1 = BitmapFactory.decodeFile(imageString);
        if (b1 != null) {
            float w1 = b1.getWidth();
            float h1 = b1.getHeight();
            int h11 = (int) h1 > 96 ? 96 : (int) h1;
            int w11 = (int) (w1 / h1 * (float) h11);
            Bitmap b11 = Bitmap.createScaledBitmap(b1, w11, h11, false);
            img.setImageBitmap(b11);
        }
    }
}

