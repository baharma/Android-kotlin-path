package com.example.sqlite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.databinding.ItemNoteBinding
import com.example.sqlite.model.Note

class NoteAdapter(private val onItemClickCallback : OnItemClickCallback)
    :RecyclerView.Adapter<NoteAdapter.NoteViewModel>() {
    var listNote = ArrayList<Note>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listNote.clear()
            }
            this.listNote.addAll(listNotes)
        }
    fun addItem(note: Note) {
        this.listNote.add(note)
        notifyItemInserted(this.listNote.size - 1)
    }
    fun updateItem(position: Int, note: Note) {
        this.listNote[position] = note
        notifyItemChanged(position, note)
    }
    fun removeItem(position: Int) {
        this.listNote.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listNote.size)
    }

    inner class NoteViewModel(private val binding: ItemNoteBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(list : Note){
            binding.apply {
                tvItemDate.text = list.date
                tvItemTitle.text = list.title
                tvItemDescription.text = list.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewModel {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewModel(binding)
    }

    override fun onBindViewHolder(holder: NoteViewModel, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int {
        return this.listNote.size
    }
    interface OnItemClickCallback{
        fun onItemClicked(selectedNote: Note?, position: Int)
    }
}