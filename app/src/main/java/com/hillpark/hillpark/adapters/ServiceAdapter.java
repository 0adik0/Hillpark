package com.hillpark.hillpark.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.hillpark.hillparkApp.R;
import com.hillpark.hillpark.model.Service;
import com.hillpark.hillpark.model.SingleCheckGroup;
import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class ServiceAdapter extends CheckableChildRecyclerViewAdapter<ServiceMenuViewHolder, SingleCheckServiceViewHolder> {

    private List<Service> services;

    public ServiceAdapter(List<SingleCheckGroup> groups, List<Service> services) {
        super(groups);
        this.services = services;
    }

    @Override
    public SingleCheckServiceViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service_radio_card, parent, false);
        return new SingleCheckServiceViewHolder(view);
    }

    @Override
    public void onBindCheckChildViewHolder(SingleCheckServiceViewHolder holder, int position,
                                           CheckedExpandableGroup group, int childIndex) {
        holder.setCardNumber(services.get(childIndex).getTitle());
        holder.setCost(services.get(childIndex).getCost());
    }

    @Override
    public ServiceMenuViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parent_serv_second, parent, false);
        return new ServiceMenuViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(ServiceMenuViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
    }
}

class SingleCheckServiceViewHolder extends CheckableChildViewHolder {

    private CheckedTextView cardRadio;
    private TextView costView;

    public SingleCheckServiceViewHolder(View itemView) {
        super(itemView);
        cardRadio = (CheckedTextView) itemView.findViewById(R.id.radioButton);
        costView = (TextView) itemView.findViewById(R.id.cost);
    }

    @Override
    public Checkable getCheckable() {
        return cardRadio;
    }

    public void setCardNumber(String cardNumber) {
        cardRadio.setText(cardNumber);
    }
    public void setCost(String cost){
        costView.setText(cost);
    }
}

class ServiceMenuViewHolder extends GroupViewHolder {

    public ServiceMenuViewHolder(View itemView) {
        super(itemView);
    }
}
