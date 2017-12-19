package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.Context;

import java.util.List;

/**
 * Created by PortatilDani on 19/12/2017.
 */

public class PersonajesInvisiblesAdapter extends PersonajesAdapter {
    public PersonajesInvisiblesAdapter(Context c, List<Personaje> list) {
        super(c, list);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        if(ListaPersonajes.get(position).isInvisible())
            holder.cara.setAlpha(0.5f);
        else
            holder.cara.setAlpha(1.f);
    }
}
