package com.hcmut.packandgo.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.activity.UserDetailActivity;
import com.hcmut.packandgo.entity.User;
import com.hcmut.packandgo.view.AlphabetListView;

public class ListViewAdapter extends ArrayAdapter<AlphabetListView> {

	private LayoutInflater inflater;
	private Context context;
	
	public ListViewAdapter(Context context, List<AlphabetListView> objects) {
		super(context, 0, objects);
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_alphabet_list, null);
			
			ListViewTag tag = new ListViewTag();
			tag.charView = ((TextView) convertView.findViewById(R.id.item_alphabet_list_character));
			tag.listView = ((ListView) convertView.findViewById(R.id.item_alphabet_list_users));
			
			convertView.setTag(tag);
		}
		
		AlphabetListView alphabetListView = getItem(position);
		
		ListViewTag tag = (ListViewTag) convertView.getTag();
		tag.charView.setText(Character.toString(alphabetListView.getCharacter()));
		
		UserAdapter adapter = new UserAdapter(context, alphabetListView.getUser());
		tag.listView.setAdapter(adapter);
		tag.listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
				User user = (User) parent.getItemAtPosition(position);
				Intent intent = new Intent(context, UserDetailActivity.class);
				intent.putExtra("User", user);
				context.startActivity(intent);
			}
			
		});
		
		return convertView;
	}
	
	private class ListViewTag {
		public TextView charView;
		public ListView listView;
	}
}
