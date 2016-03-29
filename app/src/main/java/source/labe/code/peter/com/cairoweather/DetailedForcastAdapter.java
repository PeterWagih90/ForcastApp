package source.labe.code.peter.com.cairoweather;






import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailedForcastAdapter extends BaseAdapter {

	private ArrayList<ForcastModel.ForecastDay> forCastDaysarray;
	private Context mContext;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return forCastDaysarray.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return forCastDaysarray.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		final ViewHolder viewHolder;
		if (v == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			v = inflater.inflate(R.layout.forcast_today_period, null);
			viewHolder = new ViewHolder();
			v.setTag(viewHolder);
			viewHolder.imgForCast = (ImageView) v.findViewById(R.id.imageView1);
			viewHolder.iconName = (TextView) v.findViewById(R.id.icon);

			viewHolder.fcttext = (TextView) v.findViewById(R.id.fcttext);
			viewHolder.fcttext_metric = (TextView) v.findViewById(R.id.fcttext_metric);
			viewHolder.pop = (TextView) v.findViewById(R.id.pop);
			viewHolder.title = (TextView) v.findViewById(R.id.title);

			viewHolder.right = (ImageView) ((Activity) mContext).findViewById(R.id.imageView3);
			viewHolder.left = (ImageView) ((Activity) mContext).findViewById(R.id.imageView2);

			viewHolder.rightView = (View) v.findViewById(R.id.rightView);
			viewHolder.leftView = (View) v.findViewById(R.id.leftView);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}

		final ForcastModel.ForecastDay singleOne = forCastDaysarray.get(position);
		if (position == 0) {
			viewHolder.right.setVisibility(View.VISIBLE);
			viewHolder.left.setVisibility(View.INVISIBLE);
		} else if (position == forCastDaysarray.size() - 1) {
			viewHolder.right.setVisibility(View.INVISIBLE);
			viewHolder.left.setVisibility(View.VISIBLE);
			viewHolder.leftView.setVisibility(View.GONE);
		} else {
			viewHolder.right.setVisibility(View.VISIBLE);
			viewHolder.left.setVisibility(View.VISIBLE);
			viewHolder.leftView.setVisibility(View.GONE);
		}
		viewHolder.title.setText(singleOne.nameOfTheDay);

		viewHolder.iconName.setText(singleOne.getIcon().replace("nt_", "not "));
		viewHolder.fcttext.setText(singleOne.getFcttext());
		viewHolder.fcttext_metric.setText(singleOne.getFcttext_metric());
		viewHolder.pop.setText("POP : " + singleOne.getPop());

		// viewHolder.pb1.setVisibility(View.VISIBLE);
		Picasso.with(mContext).load(forCastDaysarray.get(position).getIcon_url()).fit().error(R.drawable.ic_launcher)
				.into(viewHolder.imgForCast, new Callback() {

					@Override
					public void onSuccess() {
						// viewHolder.pb1.setVisibility(View.GONE);
					}

					@Override
					public void onError() {
						// viewHolder.pb1.setVisibility(View.GONE);

					}
				});

		// imgForCast.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent n = new Intent(mContext, DogProfile.class);
		// n.putExtra("DogObject", forCastDaysarray.get(position)
		// .getDogObject().toString());
		// if(getOwnerId() != spref.getInt("UserId",-1)){
		// n.putExtra("Mine", false);
		// }else{
		// n.putExtra("Mine", true);
		// }
		// mContext.startActivity(n);
		// // dogBreedType=extras.getString("DogBreedType");
		// // dogAge=extras.getString("DogAge");
		// // dogGender=extras.getString("DogGender");
		// // dogProfileImagPath=extras.getString("DogProfileImagPath");
		// }
		// });

		return v;
	}

	public DetailedForcastAdapter(Context c, ArrayList<ForcastModel.ForecastDay> results) {
		mContext = c;
		forCastDaysarray = results;

	}

	private static class ViewHolder {

		public View leftView;
		public View rightView;
		public ImageView left;
		public ImageView right;
		public TextView title;
		public TextView pop;
		public TextView fcttext_metric;
		public TextView fcttext;
		public TextView iconName;
		public ImageView imgForCast;
		public ProgressBar pb1;
		public TextView dogName;

	}

}
