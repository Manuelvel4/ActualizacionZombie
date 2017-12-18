package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PersonajesAdapter extends RecyclerView.Adapter<PersonajesAdapter.ViewHolder> implements View.OnClickListener,View.OnLongClickListener{

    private List<Personaje> ListaPersonajes;
    private Context context;
    private View.OnClickListener listener;
    private View.OnLongClickListener longlistener;

    public PersonajesAdapter(Context c, List<Personaje> list) {
        this.context = c;
        ListaPersonajes = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.personajes, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Personaje item = (Personaje) ListaPersonajes.get(position);
        if (item.modozombie){
            holder.cara.setImageDrawable(item.getCaraZ());
        }else{
            holder.cara.setImageDrawable(item.getCara());
        }
        holder.nombre.setText(item.getNombre());

    }

    @Override
    public int getItemCount() {
        return ListaPersonajes.size();
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

        protected ImageView cara;
        protected TextView nombre;

        public ViewHolder(View v) {
            super(v);
            cara = (ImageView) v.findViewById(R.id.foto);
            nombre = (TextView) v.findViewById(R.id.nombre);
        }
    }
}




