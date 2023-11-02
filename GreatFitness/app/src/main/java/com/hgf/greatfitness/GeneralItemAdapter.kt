import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hgf.greatfitness.data.GeneralidadResponse
import com.hgf.greatfitness.databinding.GeneralItemLayoutBinding
import com.hgf.greatfitness.data.GeneralidadesItemStructure

class GeneralItemAdapter(var items: List<GeneralidadResponse>) :
    RecyclerView.Adapter<GeneralItemAdapter.ViewHolder>() {

    class ViewHolder(private val binding: GeneralItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GeneralidadResponse) {
            binding.tvTitulo.text = item.title
            binding.tvParrafo.text = item.paragraph
            Glide.with(binding.ivImagen.context).load(item.url).into(binding.ivImagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GeneralItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
