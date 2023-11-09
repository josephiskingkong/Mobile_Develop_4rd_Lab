import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import java.util.ArrayList

data class ColorData(
    val colorName: String,
    val colorHex: Int,
)

class Adapter(
    private val context: Context,
    private val list: ArrayList<ColorData>,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    interface CellClickListener {
        fun onCellClickListener(data: ColorData)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val colorHex: View = view.findViewById(R.id.colorView)
        val colorName: TextView = view.findViewById(R.id.colorText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)

        val holder = ViewHolder(view)

        view.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                ?: return@setOnClickListener
            cellClickListener.onCellClickListener(list[position])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        val color = Color.rgb(
            (data.colorHex and 0xFF0000) shr 16,
            (data.colorHex and 0x00FF00) shr 8,
            (data.colorHex and 0x0000FF)
        )
        holder.colorHex.setBackgroundColor(color)
        holder.colorName.text = data.colorName
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
    }
}
