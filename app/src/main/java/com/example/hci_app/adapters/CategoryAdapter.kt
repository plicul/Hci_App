import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hci_app.R
import com.example.hci_app.databinding.CategoryItemBinding

class CategoryAdapter(private val list: ArrayList<String>,
                      private val context: Context,
                      private val onItemClick: ((Int) -> Unit)) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        with(holder) {

            holder.binding.textView.text = item.toString()

            holder.binding.imageView.setImageResource(R.mipmap.ic_launcher)

            holder.itemView.setOnClickListener{
                onItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}