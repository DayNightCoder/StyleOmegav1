package com.example.sachinpc.styleomegav10;


        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;


/**
 * Created by Oclemmy on 4/29/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class myHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    ImageView img;


    public myHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        img= (ImageView) itemView.findViewById(R.id.movieImage);

    }
}
