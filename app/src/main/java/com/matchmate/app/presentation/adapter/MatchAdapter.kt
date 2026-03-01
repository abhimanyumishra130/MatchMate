import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.matchmate.app.core.utils.MatchScoreCalculator
import com.matchmate.app.core.utils.MatchStatus
import com.matchmate.app.core.utils.MyData
import com.matchmate.app.core.utils.loadImage
import com.matchmate.app.databinding.ItemLayoutPersonBinding
import com.matchmate.app.domain.model.Person

typealias LikeClickListener = (Person, Int) -> Unit
typealias MessageClickListener = (Person) -> Unit
typealias CloseClickListener = (Person) -> Unit

class MatchAdapter : ListAdapter<Person, MatchAdapter.ViewHolder>(DiffCallback()) {

    var onLikeClicked: LikeClickListener? = null
    var onMessageClicked: MessageClickListener? = null
    var onCloseClicked: CloseClickListener? = null

    inner class ViewHolder(private val binding: ItemLayoutPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Person, position: Int) {
            binding.apply {
                ivProfile.loadImage(item.profileImage)
                tvName.text = buildString { append(item.fullName).append(", ").append(item.age) }
                tvCity.text = buildString { append(item.city).append(", ").append(item.country) }
                tvEducation.text = buildString { append(item.education) }

                matchScore.text = "Match score : ${MatchScoreCalculator.calculateMatchScore(MyData.myData, item)}%"

                ivLike.setOnClickListener {
                    onLikeClicked?.invoke(item, position)
                }

                ivMessage.setOnClickListener {
                    Toast.makeText(root.context, "Message ${item.fullName}", Toast.LENGTH_SHORT)
                        .show()
                    onMessageClicked?.invoke(item)
                }

                ivClose.setOnClickListener {
                    Toast.makeText(root.context, "Closed ${item.fullName}", Toast.LENGTH_SHORT)
                        .show()
                    onCloseClicked?.invoke(item)
                }

                if (item.status == MatchStatus.ACCEPTED){
                    ivLike.visibility = ViewGroup.GONE
                    matchSuccess.visibility = ViewGroup.VISIBLE
                    ivMessage.visibility = ViewGroup.GONE
                    ivClose.visibility = ViewGroup.GONE
                }else{
                    ivLike.visibility = ViewGroup.VISIBLE
                    matchSuccess.visibility = ViewGroup.GONE
                    ivMessage.visibility = ViewGroup.VISIBLE
                    ivClose.visibility = ViewGroup.VISIBLE
                }

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutPersonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }


    class DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
}
