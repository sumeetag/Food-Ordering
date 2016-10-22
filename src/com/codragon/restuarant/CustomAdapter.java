package com.codragon.restuarant;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter extends ArrayAdapter<Model> implements Filterable{

	private List<Model> list;
	private List<Model> li;
	private final Activity context;
	public static int no;
	String fontPath = "fonts/Face Your Fears.ttf";

	// Model[] modelItems = null;

	// Context context;
	
	public CustomAdapter(Activity context, List<Model> list) {
	    super(context, R.layout.row, list);
	    this.context = context;
	    this.list = list;
	    this.li = list;
	  }
	
	public int getCount () {
	    return list.size ();
	}
	

	static class ViewHolder {
	    protected TextView food, price;
	    protected CheckBox checkbox;
	    protected ImageView veg;
	    protected Button add, sub;
	    
	    
	  }
	/*
	public CustomAdapter(Context context, Model[] resource) {
		super(context, R.layout.row, resource);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.modelItems = resource;
	}
	*/
	
	 @Override
	  public View getView(final int position, View convertView, ViewGroup parent) {
	   // View view = null;
	   // if (convertView == null) {
	      LayoutInflater inflator = context.getLayoutInflater();
	      convertView = inflator.inflate(R.layout.row, null);
	      final ViewHolder viewHolder = new ViewHolder();
	      //Typeface tf = Typeface.createFromAsset(context.getAssets() , fontPath);
	      viewHolder.food = (TextView) convertView.findViewById(R.id.textView1);
	     // viewHolder.food.setTypeface(tf);
	      viewHolder.price = (TextView) convertView.findViewById(R.id.price);
	      //viewHolder.rating = (TextView) convertView.findViewById(R.id.textView3);
	      //viewHolder.orders = (TextView) convertView.findViewById(R.id.textView2);
	      //viewHolder.serves = (TextView) convertView.findViewById(R.id.serves);
	      //viewHolder.nof = (TextView) view.findViewById(R.id.nof);
	      viewHolder.veg = (ImageView) convertView.findViewById(R.id.imageView1);
	      viewHolder.add = (Button) convertView.findViewById(R.id.addfood);
	      viewHolder.sub = (Button) convertView.findViewById(R.id.subfood);
	      viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.checkBox1);
	      viewHolder.checkbox.setButtonDrawable(R.drawable.checkbox_unchecked);
	      viewHolder.add.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stuboas
				//Toast.makeText(context, "You have selected", Toast.LENGTH_SHORT).show();
				//list.get(position).setSelected(true);
				FoodList no_of_orders = new FoodList();
				no_of_orders.nof(list.get(position).getFood().trim().replaceAll(" ", "_").replace("null", ""), context, viewHolder.checkbox);
				
			}
		});
	      viewHolder.sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FoodList no_of_orders = new FoodList();
				no_of_orders.nofnew(list.get(position).getFood().trim().replaceAll(" ", "_").replace("null", ""), context);
			}
		});
	      viewHolder.checkbox
	          .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
 
	            @Override
	            public void onCheckedChanged(CompoundButton buttonView,
	                boolean isChecked) {
	            	if(isChecked){
	            		viewHolder.checkbox.setButtonDrawable(R.drawable.checkbox_checked_icon);
	            		Toast.makeText(context, "Pls select the Quantity", Toast.LENGTH_SHORT).show();
	            		FoodList fl = new FoodList();
	            		//fl.name(list.get(position).getFood().trim().replaceAll(" ", "_").replace("null", ""),FoodList.table);
	            		System.out.println("dfdsfaddaf"+position);
	            		System.out.println(list.get(position).getFood().trim().replaceAll(" ", "_"));
	            		//System.out.println(FoodList.table);
	            	}else if(!isChecked){
	            		viewHolder.checkbox.setButtonDrawable(R.drawable.checkbox_unchecked);
	            		FoodList f2 = new FoodList();
	            		//f2.notname(list.get(position).getFood().trim().replaceAll(" ", "_").replace("null", ""), FoodList.table);
	            		System.out.println(list.get(position).getFood());
	            	}
	           
	              Model element = (Model) viewHolder.checkbox
	                  .getTag();
	              element.setSelected(buttonView.isChecked());
	            }
	            
	          });
	      convertView.setTag(viewHolder);
	      viewHolder.checkbox.setTag(list.get(position));
	      //System.out.println("drqqqqqqqqqqqqqqqq"+(viewHolder.checkbox.setTag(list.get(position).toString()));
	  //  } else {
	      //view = convertView;
	      ((ViewHolder) convertView.getTag()).checkbox.setTag(list.get(position));
	      
	  //  }
	    ViewHolder holder = (ViewHolder) convertView.getTag();
	   /*
	    holder.food.setText(list.get(position).food);
	    holder.rating.setText(list.get(position).rating);
	    holder.orders.setText(list.get(position).order);
	    holder.price.setText(list.get(position).price);
	    holder.serves.setText(list.get(position).serves);
	    holder.checkbox.setChecked(list.get(position).isSelected());
	    */
	    holder.food.setText(list.get(position).getFood().replace("null", ""));
	    //holder.rating.setText(list.get(position).getRating());
	   // holder.orders.setText(list.get(position).getOrder());
	    holder.price.setText(list.get(position).getPrice());
	    //holder.serves.setText(list.get(position).getServes());
	    holder.checkbox.setChecked(list.get(position).isSelected());
	    
	    return convertView;
	  }
	 
	 @Override
		public Filter getFilter() {
			Filter filter = new Filter() {

				@SuppressWarnings("unchecked")
				@Override
				protected void publishResults(CharSequence constraint,
						FilterResults results) {

					list = (List<Model>) results.values; // has
																			// the
																			// filtered
																			// values
					notifyDataSetChanged(); // notifies the data with new filtered
											// values
				}

				@Override
				protected FilterResults performFiltering(CharSequence constraint) {
					FilterResults results = new FilterResults(); // Holds the
																	// results of a
																	// filtering
																	// operation in
																	// values
					ArrayList<Model> FilteredArrList = new ArrayList<Model>();

					if (li == null) {
						li = (List<Model>)(list); // saves
																					// the
																					// original
																					// data
																					// in
																					// mOriginalValues
					}

					/********
					 * 
					 * If constraint(CharSequence that is received) is null returns
					 * the mOriginalValues(Original) values else does the Filtering
					 * and returns FilteredArrList(Filtered)
					 * 
					 ******/
					if (constraint == null || constraint.length() == 0) {

						// set the Original result to return
						results.count = li.size();
						results.values = li;
					} else {
						constraint = constraint.toString().toLowerCase();
						for (int i = 0; i < li.size(); i++) {
							String data = li.get(i).food;
							if (data.toLowerCase()
									.contains(constraint.toString())) {
								FilteredArrList
										.add(new Model(
												li.get(i).food,li.get(i).price));
							}
						}
						// set the Filtered result to return
						results.count = FilteredArrList.size();
						results.values = FilteredArrList;
					}
					return results;
				}

			};
			return filter;
		}
}