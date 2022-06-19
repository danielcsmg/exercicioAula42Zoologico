package br.com.zup.zoologico.cadastro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.zoologico.databinding.AnimalLayoutBinding
import br.com.zup.zoologico.model.Animal

class AnimaisAdapter(
    val listaAnimais: MutableList<Animal>,
    val irParaDetalheAnimal: (animal: Animal) -> Unit,
    ): RecyclerView.Adapter<AnimaisAdapter.ViewHolder>() {
    class ViewHolder(val binding: AnimalLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun exibirAnimais(animal: Animal){
            binding.tvNomeAnimal.text = animal.getNome()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimalLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = listaAnimais[position]
        holder.exibirAnimais(animal)
        holder.binding.cvAnimal.setOnClickListener{
            irParaDetalheAnimal(animal)
        }
    }

    override fun getItemCount(): Int = listaAnimais.size

    fun atualizarListaAnimais(animal: Animal){
        listaAnimais.add(animal)
        notifyDataSetChanged()
    }
}