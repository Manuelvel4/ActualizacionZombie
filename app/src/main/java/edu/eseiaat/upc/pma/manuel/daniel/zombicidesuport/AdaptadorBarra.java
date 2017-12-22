package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Dani on 22/12/2017.
 */

public class AdaptadorBarra extends RecyclerView.Adapter<AdaptadorBarra.ViewHolderBarra> {


    ArrayList<BARRA> lista;

    public AdaptadorBarra(ArrayList<BARRA> lista) {
        this.lista = lista;
    }



    @Override
    public ViewHolderBarra onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.barra, null, false);
        return new ViewHolderBarra(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderBarra holder, int position) {

        holder.bala.setImageResource(lista.get(position).getBala());
        holder.nivel.setImageResource(lista.get(position).getNivel());


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderBarra extends RecyclerView.ViewHolder {

        ImageView bala, nivel;

        public ViewHolderBarra(View itemView) {
            super(itemView);
            bala = (ImageView)itemView.findViewById(R.id.Puntero);
            nivel = (ImageView) itemView.findViewById(R.id.Nivel);


        }
    }


}
