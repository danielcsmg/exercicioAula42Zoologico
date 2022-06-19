package br.com.zup.zoologico.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.zoologico.MainActivity
import br.com.zup.zoologico.cadastro.adapter.AnimaisAdapter
import br.com.zup.zoologico.const.MENSAGEM_ERRO_DESCRICAO
import br.com.zup.zoologico.const.MENSAGEM_ERRO_NOME
import br.com.zup.zoologico.databinding.FragmentCadastroAnimalBinding
import br.com.zup.zoologico.model.Animal

class CadastroAnimalFragment : Fragment() {
    private lateinit var binding: FragmentCadastroAnimalBinding
    private val animaisAdapter: AnimaisAdapter by lazy { AnimaisAdapter(mutableListOf(), this::acessarDetalheAnimal) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastroAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exibirRecyclerView()
        cadastrar()
        alterarAppBar()
    }

    private fun exibirRecyclerView() {
        binding.rvAnimais.adapter = animaisAdapter
        binding.rvAnimais.layoutManager = LinearLayoutManager(context)
    }

    private fun cadastrar() {
        binding.btnAdicionar.setOnClickListener {
            adicionarAnimal()
        }
    }

    private fun adicionarAnimal() {
        val animal = recuperarCamposAnimal()

        animal?.let {
            animaisAdapter.atualizarListaAnimais(it)
        }
    }

    private fun recuperarCamposAnimal(): Animal? {
        val nome = binding.etNomeAnimal.text.toString()
        val descricao = binding.etDescricaoAnimal.text.toString()
        if (nome.isNotBlank() && descricao.isNotBlank()) {
            limparCampos()
            return Animal(nome, descricao)
        }
        mensagemErro(nome, descricao)
        return null
    }

    private fun limparCampos() {
        binding.etNomeAnimal.text.clear()
        binding.etDescricaoAnimal.text.clear()
    }

    private fun mensagemErro(nome: String, descricao: String) {
        binding.etNomeAnimal.error = if (nome.isBlank()) MENSAGEM_ERRO_NOME else null
        binding.etDescricaoAnimal.error = if (descricao.isBlank()) MENSAGEM_ERRO_DESCRICAO else null
    }

    private fun acessarDetalheAnimal(animal: Animal){
        val action = CadastroAnimalFragmentDirections.actionCadastroAnimalFragmentToAnimaisCadastradosFragment(animal)
        findNavController().navigate(action)
    }

    private fun alterarAppBar(){
        (activity as MainActivity).supportActionBar?.title = "Zoológico"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
