package cz.muni.fi.pv256.hw9.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import cz.muni.fi.pv256.hw9.R
import cz.muni.fi.pv256.hw9.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ITEM = "item"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vm: DetailViewModel by viewModels()

        vm.user.observe(
            this,
            {
                binding.img.load(it.image)
                binding.title.text = it.name
                binding.status.text = String.format(getString(R.string.status), it.status)
                binding.gender.text = String.format(getString(R.string.gender), it.gender)
                binding.species.text = String.format(getString(R.string.species), it.species)

                supportActionBar?.apply {
                    setDisplayShowTitleEnabled(true)
                    title = it.name
                }
            }
        )

        intent.extras?.apply {
            vm.setCharacterId(getInt(ITEM))
        }
    }
}
