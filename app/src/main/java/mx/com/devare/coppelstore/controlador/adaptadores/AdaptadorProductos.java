package mx.com.devare.coppelstore.controlador.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mx.com.devare.coppelstore.R;
import mx.com.devare.coppelstore.modelo.Producto;

/**
 * Created by Angel on 07/10/2017.
 */

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProducto> {

    Context mContext;
    List<Producto> mProductoList;

    public AdaptadorProductos(Context mContext, List<Producto> mProductoList,OnSetOnclickListener mOnSetOncliclListener) {
        this.mContext = mContext;
        this.mProductoList = mProductoList;
        this.mOnSetOncliclListener=mOnSetOncliclListener;

    }

    @Override
    public ViewHolderProducto onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lists_producto, parent, false);
        return new ViewHolderProducto(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolderProducto holder, int position) {
        Producto mProducto = mProductoList.get(position);
        Glide.with(mContext).load(mProducto.getUriImagen()).into(holder.iv_item_producto);
        holder.tv_item_nombre_producto.setText(mProducto.getNombre());
        holder.tv_item_precio_producto.setText(mProducto.getPrecio());
        holder.tv_item_stock_producto.setText(mProducto.getStock());

    }

    @Override
    public int getItemCount() {
        return mProductoList.size();
    }

    public Producto obtenerProducto(int posicion) {
        if (posicion != RecyclerView.NO_POSITION) {
            return mProductoList.get(posicion);
        } else {
            return null;
        }
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_item_producto;
        TextView tv_item_precio_producto, tv_item_nombre_producto, tv_item_stock_producto;

        public ViewHolderProducto(View itemView) {
            super(itemView);

            iv_item_producto = (ImageView) itemView.findViewById(R.id.iv_item_producto);
            tv_item_precio_producto = (TextView) itemView.findViewById(R.id.tv_item_precio_producto);
            tv_item_nombre_producto = (TextView) itemView.findViewById(R.id.tv_item_nombre_producto);
            tv_item_stock_producto = (TextView) itemView.findViewById(R.id.tv_item_stock_producto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnSetOncliclListener.onClick(this, obtenerProducto(getAdapterPosition()));
        }
    }

    public interface OnSetOnclickListener {
        public void onClick(ViewHolderProducto holderProducto, Producto mProducto);
    }

    public OnSetOnclickListener mOnSetOncliclListener;
}
