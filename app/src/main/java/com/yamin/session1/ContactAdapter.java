package com.yamin.session1;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.tamir7.contacts.Contact;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Yamin on 9/1/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    List<Contact> contacts;
    Context context;

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_contact, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt1.setText(contacts.get(position).getDisplayName());
        if (contacts.get(position).getPhoneNumbers().size() != 0)
            holder.txtPhone.setText(contacts.get(position).getPhoneNumbers().get(0).getNumber());

        if (contacts.get(position).getPhotoUri() != null)
            holder.img1.setImageURI(Uri.parse(contacts.get(position).getPhotoUri()));
//        if (contacts.get(position).getPhotoUri() !=null)
//        Glide.with(context)
//                .load(contacts.get(position).getPhotoUri())
//                .apply(new RequestOptions()
//                        .centerCrop())
//                        .into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img1;
        TextView txt1;
        TextView txtPhone;

        public MyViewHolder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.image1);
            txt1 = itemView.findViewById(R.id.text1);
            txtPhone = itemView.findViewById(R.id.text_phone);
        }
    }
}
