package ly.generalassemb.drewmahrt.shoppinglistwithsearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by joey on 7/14/16.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.RecyclerViewHolder> {

    private List<GroceryListItem> items;

    public SearchListAdapter(List<GroceryListItem> items) {

        this.items = items;
    }

    @Override
    public SearchListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocerylistitems, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchListAdapter.RecyclerViewHolder holder, final int position) {
        final GroceryListItem item = items.get(position);

        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.type.setText(item.getType());
        holder.price.setText("$"+ item.getPrice());

        holder.setOnClickListener(new RecyclerViewHolder.OnClickListener() {
            @Override
            public void onClick(final View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //handle long presses

                } else {
                    //handle clicks

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView type;
        TextView name;
        TextView description;
        TextView price;

        OnClickListener onClickListener;

        public RecyclerViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            type = (TextView) view.findViewById(R.id.type);
            price = (TextView) view.findViewById(R.id.price);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }
        /*
          This interface is used in conjunction with onClick and onLongClick to implement them within onBindViewHolder
        */
        public interface OnClickListener {

            void onClick(View v, int position, boolean isLongClick);
        }

        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            onClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
