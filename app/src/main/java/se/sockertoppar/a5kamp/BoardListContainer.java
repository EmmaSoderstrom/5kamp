//package se.sockertoppar.a5kamp;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by User on 2017-06-05.
// */
//
//public class BoardListContainer  extends ArrayAdapter<Player> {
//
//    private final Context context;
//    private ArrayList<Player> player;
//
//    //ViewHolder holder;
//    ImageView imagePicView;
//
//
//    public BoardListContainer(Context context, ArrayList<Player> startPerson) {
//        super(context, 0, startPerson);
//
//        this.context = context;
//        player = startPerson;
//
//    }
//
//    @Override
//    public int getCount() {
//        return player.size();
//    }
//
//    @Override
//    public Player getItem(int position) {
//        return player.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        View rowView;
//
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            rowView = inflater.inflate(R.layout.main_list_item, null, true);
//        } else {
//            rowView = convertView;
//        }
//
//        imagePicView = (ImageView) rowView.findViewById(R.id.list_pic);
//        TextView textNameView = (TextView) rowView.findViewById(R.id.list_name);
//        TextView textTimwView = (TextView) rowView.findViewById(R.id.list_time);
//        TextView textDistansView = (TextView) rowView.findViewById(R.id.list_distans);
//
//
//        //text tid
//        String panelTime = context.getString(R.string.panel_time);
//        textTimwView.setText(checkTimeDistance(person.get(position)) + " " + panelTime);
//
//        //text distans
//        int thisUserDistans = player.get(position).getDistansBetween();
//        int distansConverted;
//        String distansValue;
//
//        String panelDistansM = context.getString(R.string.panel_distans_m);
//        String panelDistansKm = context.getString(R.string.panel_distans_km);
//        String panelDistansMil = context.getString(R.string.panel_distans_mil);
//
//
//        return rowView;
//    }
//}
