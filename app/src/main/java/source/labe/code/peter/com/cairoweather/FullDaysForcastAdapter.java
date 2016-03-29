package source.labe.code.peter.com.cairoweather;

import java.util.ArrayList;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FullDaysForcastAdapter extends BaseAdapter {

	private ArrayList<ForcastModel.PeriodSimpleForcast> forCastDaysarray;
	private Context mContext;

	@Override
	public int getCount() {
		return forCastDaysarray.size();
	}

	@Override
	public Object getItem(int position) {

		return forCastDaysarray.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public String toString() {
		return "FullDaysForcastAdapter{" +
				"forCastDaysarray=" + forCastDaysarray +
				", mContext=" + mContext +
				'}';
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		final ViewHolder viewHolder;
		if (v == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			v = inflater.inflate(R.layout.forcast_all_days, null);
			viewHolder = new ViewHolder();
			v.setTag(viewHolder);
			viewHolder.imgForCast = (ImageView) v.findViewById(R.id.imageView1);
			viewHolder.iconName = (TextView) v.findViewById(R.id.icon);
			viewHolder.title = (TextView) v.findViewById(R.id.date);
			viewHolder.high = (TextView) v.findViewById(R.id.High);
			viewHolder.low = (TextView) v.findViewById(R.id.low);
			viewHolder.right = (ImageView) ((Activity) mContext).findViewById(R.id.imageView5);
			viewHolder.left = (ImageView) ((Activity) mContext).findViewById(R.id.imageView4);
			viewHolder.rawData = (TextView) v.findViewById(R.id.dataraw);
			viewHolder.rightView = (View) v.findViewById(R.id.rightView);
			viewHolder.leftView = (View) v.findViewById(R.id.leftView);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}

		final ForcastModel.PeriodSimpleForcast singleOne = forCastDaysarray.get(position);
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
		viewHolder.title.setText(singleOne.getDay() + " " + singleOne.getMonthname_short() + " " + singleOne.getYear());
		viewHolder.iconName.setText(singleOne.getIcon());
		viewHolder.high.setText(singleOne.getCelsius_high() + "°C / " + singleOne.getFahrenheit_high() + "°F");
		viewHolder.low.setText(singleOne.getCelsius_low() + "°C / " + singleOne.getFahrenheit_low() + "°F");

		viewHolder.rawData.setText("POP : " + singleOne.pop + "\nAverage Humidity : " + singleOne.avehumidity + "\n"
				+ "Average Wind : " + singleOne.avewind_mph + " mph / " + singleOne.avewind_kph + " kph" + "\n"
				+ "Average wind direction : " + singleOne.avewind_dir + "\n Average wind degree : "
				+ singleOne.avewind_degree + "\n" + "Max Wind : " + singleOne.maxwind_mph + " mph / "
				+ singleOne.maxwind_kph + " kph" + "\n" + "Max wind direction : " + singleOne.maxwind_dir
				+ "\nMax wind degree : " + singleOne.maxwind_degree + "\n ");

		Picasso.with(mContext).load(forCastDaysarray.get(position).getIcon_url()).fit().error(R.drawable.ic_launcher)
				.into(viewHolder.imgForCast, new Callback() {

					@Override
					public void onSuccess() {

					}

					@Override
					public void onError() {

					}
				});

		return v;
	}

	public FullDaysForcastAdapter(Context c, ArrayList<ForcastModel.PeriodSimpleForcast> results) {
		mContext = c;
		forCastDaysarray = results;
	}

	private static class ViewHolder {
		public TextView rawData;
		public View leftView;
		public View rightView;
		public ImageView left;
		public ImageView right;
		public TextView low;
		public TextView high;
		public TextView title;
		public TextView iconName;
		public ImageView imgForCast;
	}

}
