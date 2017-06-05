package ph.edu.up.flowers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fulltime on 02/06/2017.
 */

public class ListFlowerAdapter extends BaseAdapter {
    private Context context;
    private List<Flower> flowerList;

    public ListFlowerAdapter(Context context, List<Flower> flowerList) {
        this.context = context;
        this.flowerList = flowerList;
    }

    @Override
    public int getCount() {
        return flowerList.size();
    }

    @Override
    public Object getItem(int position) {
        return flowerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return flowerList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.content_flower, null);
        TextView textName = (TextView) view.findViewById(R.id.item_name);
        TextView textEase = (TextView) view.findViewById(R.id.item_ease);
        /*TextView textInst = (TextView) view.findViewById(R.id.item_instructions);*/
        textName.setText(flowerList.get(position).getName());
        textEase.setText(flowerList.get(position).getEase());
        /*textInst.setText(flowerList.get(position).getInstructions());*/
        return view;
    }
}
