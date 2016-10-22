/*package com.codragon.restuarant;

import java.util.List;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class RatingCustomAdapter extends ArrayAdapter<RatingModel> {

	private final List<RatingModel> list;
	private final Activity context;

	// Model[] modelItems = null;

	// Context context;

	public RatingCustomAdapter(Activity context, List<RatingModel> list) {
		super(context, R.layout.giverating, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView food;
		protected RatingBar rb;

	}

	
	 * public CustomAdapter(Context context, Model[] resource) { super(context,
	 * R.layout.row, resource); // TODO Auto-generated constructor stub
	 * this.context = context; this.modelItems = resource; }
	 
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.giverating, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.food = (TextView) view.findViewById(R.id.showfood);

			viewHolder.rb = (RatingBar) view.findViewById(R.id.giverating);

			viewHolder.rb
					.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
						public void onRatingChanged(RatingBar ratingBar,
								float rating, boolean fromUser) {

							// txtRatingValue.setText(String.valueOf(rating));
							System.out.println(rating);
							RatingFragment update = new RatingFragment();
							update.updateorders(list.get(position).getFood().trim().replaceAll(" ", "_"),
									rating);

						}
					});

			view.setTag(viewHolder);
		} else {
			view = convertView;
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.food.setText(list.get(position).getFood());
		return view;
	}

}
*/