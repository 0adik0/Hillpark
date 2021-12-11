package com.hillpark.hillpark.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CheckedTextView;

import com.hillpark.hillparkApp.R;
import com.hillpark.hillpark.model.Card;
import com.hillpark.hillpark.model.SingleCheckGroup;
import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class CardAdapter extends CheckableChildRecyclerViewAdapter<ServiceMenuViewHolder, SingleCheckServiceViewHolder> {

    private List<Card> cards;

    public CardAdapter(List<SingleCheckGroup> groups, List<Card> cards) {
        super(groups);
        this.cards = cards;
    }

    @Override
    public SingleCheckServiceViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_radio_card, parent, false);
        return new SingleCheckServiceViewHolder(view);
    }

    @Override
    public void onBindCheckChildViewHolder(SingleCheckServiceViewHolder holder, int position,
                                           CheckedExpandableGroup group, int childIndex) {
        holder.setCardNumber(cards.get(childIndex).getName());
    }

    @Override
    public ServiceMenuViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parent_card, parent, false);
        return new ServiceMenuViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(ServiceMenuViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
    }
}

class SingleCheckCardViewHolder extends CheckableChildViewHolder {

    private CheckedTextView cardRadio;

    public SingleCheckCardViewHolder(View itemView) {
        super(itemView);
        cardRadio = (CheckedTextView) itemView.findViewById(R.id.radioButton);
    }

    @Override
    public Checkable getCheckable() {
        return cardRadio;
    }

    public void setCardNumber(String cardNumber) {
        cardRadio.setText(cardNumber);
    }
}

class MenuViewHolder extends GroupViewHolder {

    public MenuViewHolder(View itemView) {
        super(itemView);
    }
}

