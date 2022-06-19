package br.com.zup.zoologico.cadastro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.zoologico.MainActivity
import br.com.zup.zoologico.R
import br.com.zup.zoologico.databinding.FragmentAnimaisCadastradosBinding
import br.com.zup.zoologico.model.Animal

class AnimaisCadastradosFragment : Fragment() {
    private lateinit var binding: FragmentAnimaisCadastradosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimaisCadastradosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animal = recuperarDadosAnimal()
        mostrarInformacoesAnimal(animal)
        alterarAppBar()

    }

    private fun recuperarDadosAnimal(): Animal {
        val args = AnimaisCadastradosFragmentArgs.fromBundle(requireArguments())
        return args.animal
    }

    private fun mostrarInformacoesAnimal(animal: Animal){
        binding.tvNomeAnimal.text = animal.getNome()
        binding.tvDescricaoAnimal.text = animal.getDescricao()
    }

    private fun alterarAppBar(){
        (activity as MainActivity).supportActionBar?.title = "Detalhe"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}