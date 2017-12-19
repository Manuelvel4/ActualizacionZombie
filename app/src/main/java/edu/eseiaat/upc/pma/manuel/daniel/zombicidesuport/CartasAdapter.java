package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by PortatilDani on 11/12/2017.
 */

public class CartasAdapter extends RecyclerView.Adapter<CartasAdapter.ViewHolder> implements View.OnClickListener,View.OnLongClickListener{
        private List<Carta> listaCartas;
        private Context context;
        private View.OnClickListener listener;
        private View.OnLongClickListener longlistener;

        public CartasAdapter(Context c, List<Carta> list) {
            this.context = c;
            listaCartas = list;
        }


        @Override
        public edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.CartasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartas, parent, false);
            final ViewHolder vh = new ViewHolder(v);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
            return vh;
        }

        @Override
        public void onBindViewHolder(edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.CartasAdapter.ViewHolder holder, int position) {
            Carta item = (Carta) listaCartas.get(position);
            holder.carta.setImageResource(item.getCarta());
        }

        @Override
        public int getItemCount() {
            return listaCartas.size();
        }


        public void setOnClickListener(View.OnClickListener listener){
            this.listener=listener;
        }
        @Override
        public void onClick(View view) {
            if(listener!=null){
                listener.onClick(view);
            }
        }

        public void setOnLongClickListener(View.OnLongClickListener longlistener){
            this.longlistener=longlistener;
        }
        @Override
        public boolean onLongClick(View view) {
            if(longlistener!=null){
                return longlistener.onLongClick(view);
            }
            return false;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

                protected ImageView carta;
                public ViewHolder(View v) {
                    super(v);
                    carta = (ImageView) v.findViewById(R.id.cartas);

                }
            }
}
