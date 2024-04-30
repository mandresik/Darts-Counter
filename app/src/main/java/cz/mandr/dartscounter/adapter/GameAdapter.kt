package cz.mandr.dartscounter.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.mandr.dartscounter.database.Game
import cz.mandr.dartscounter.databinding.GameItemBinding

class GameAdapter(private val dataSet: ArrayList<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    lateinit var binding: GameItemBinding


    private var deleteClickListener: GameDeleteClickListener? = null

    interface GameDeleteClickListener {
        fun onDeleteClick(game: Game)
    }

    fun setDeleteClickListener(listener: GameDeleteClickListener) {
        deleteClickListener = listener
    }


    inner class ViewHolder(private val binding: GameItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(game: Game){
            binding.game = game

            binding.buttonDelete.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    deleteClickListener?.onDeleteClick(dataSet[position])
                }
            }


        }
    }


    // creates new views
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = GameItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // replaces the contents of a view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    // returns the size of dataset
    override fun getItemCount() = dataSet.size


    fun updateData(newData: List<Game>) {
        dataSet.clear()
        dataSet.addAll(newData)
        notifyDataSetChanged()
    }

}