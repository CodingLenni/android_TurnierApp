package symphome.de.turnierapp;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Reck on 09.05.2017.
 */

public class Turnier implements Parcelable {

    private String _id;
    private String description;
    private String imageUri;

    public Turnier(String _id, String description, String imageuri) {
        super();
        this._id = _id;
        this.description = description;
        this.imageUri = imageuri;
    }

    public Turnier(Parcel parcel) {
        // dieselbe Reihenfolge wie bei writeToParcel!!
       _id = parcel.readString();
       description = parcel.readString();
       imageUri = parcel.readString();
    }

    public static final Creator<Turnier> CREATOR = new Creator<Turnier>() {

        @Override
        public Turnier createFromParcel(Parcel parcel) {
            return new Turnier(parcel);
        }

        @Override
        public Turnier[] newArray(int size) {
            return new Turnier[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public String getDescription() {
        return description;
    }
    public String getImageUri() { return imageUri; }

    public String toString() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(description);
        parcel.writeString(imageUri);
    }
}
