package com.example.tomek.firetest.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomek.firetest.model.Dog;
import com.example.tomek.firetest.R;
import com.example.tomek.firetest.activities.ShowAnimalDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 17/10/16.
 */

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder> {


    private ArrayList<Dog> animals;


    public AnimalsAdapter() {
        this.animals = new ArrayList<>();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {

        android.content.Context context;
        CardView cvAnimals;
        TextView tvAnimalBirthdate;
        TextView tvAnimalName;


        public AnimalViewHolder(View view) {
            super(view);
            context = view.getContext();
            tvAnimalName = (TextView) view.findViewById(R.id.tv_animal_name);
            tvAnimalBirthdate = (TextView) view.findViewById(R.id.tv_animal_birthdate);
            cvAnimals = (CardView) view.findViewById(R.id.cv_animals);
        }

    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);

        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AnimalViewHolder holder, int position) {
        final Dog dog = animals.get(position);
        holder.tvAnimalName.setText(dog.getName());
        holder.tvAnimalBirthdate.setText(dog.getBirthdate());
       // holder.tvAnimalName.setTextColor(Color.BLUE);


        holder.cvAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context, ShowAnimalDetails.class);
                intent.putExtra(ShowAnimalDetails.ANIMAL_NAME, dog.getName());
                holder.context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public void setAnimals(List<Dog> animals) {
        this.animals.clear();
        this.animals.addAll(animals);
        notifyDataSetChanged();
    }
}
