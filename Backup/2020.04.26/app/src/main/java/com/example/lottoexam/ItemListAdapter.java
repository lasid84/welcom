package com.example.lottoexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemListAdapter extends BaseAdapter {

    private List<ItemList> mData;
    private Map<String, Integer> mItemImageMap;

    public ItemListAdapter(List<ItemList> Data) {

        this.mData = Data;
        this.mItemImageMap = new HashMap<>();
        mItemImageMap.put("1", R.drawable.qrcode);
        mItemImageMap.put("2", R.drawable.write);
        mItemImageMap.put("3", R.drawable.confirm);
        mItemImageMap.put("4", R.drawable.write);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

            ImageView itemImage = convertView.findViewById(R.id.img_item);
            TextView itemText = convertView.findViewById(R.id.item_text);

            holder.itemImage = itemImage;
            holder.itemText = itemText;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemList itemList = mData.get(position);
        holder.itemText.setText(itemList.getItemname());
        holder.itemImage.setImageResource(mItemImageMap.get(itemList.getIcon()));

        //LayoutInflater -> 외부에서 layout.xml 가져오기
        return convertView;
    }

    static class ViewHolder {
        ImageView itemImage;
        TextView itemText;
    }
}
