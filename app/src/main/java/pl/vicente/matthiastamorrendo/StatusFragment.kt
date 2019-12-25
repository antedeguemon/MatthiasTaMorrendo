package pl.vicente.matthiastamorrendo


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import kotlinx.android.synthetic.main.fragment_status.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val call = RetrofitInitializer().glicoseService().list()
        call.enqueue(object: Callback<List<GlicoseEntry>?> {
            override fun onResponse(call: Call<List<GlicoseEntry>?>?,
                                    response: Response<List<GlicoseEntry>?>?) {
                response?.body()?.let {
                    val glicoses: List<GlicoseEntry> = it
                    val currentGlicose = glicoses.last()
                    glicoseText.text = currentGlicose.value.toString()+" mg/dL"
                    dateText.text = "Atualizado em " + currentGlicose.date
                    setStatus(currentGlicose.value)
                    matthiasStatus.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<GlicoseEntry>?>?,
                                   t: Throwable?) {
                Log.e("WAT", t?.message)
            }
        })


        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        return inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun glicoses(): List<GlicoseEntry> {
        return listOf(GlicoseEntry("2019-01-01", 200), GlicoseEntry("2019-01-02", 250))
    }

    private fun setStatus(value: Int) {
        if (value > 120) {
            statusText.text =  "tá com hiperglicemia"
            statusText.setTextColor(Color.RED)
        } else if (value < 80) {
            statusText.text =  "tá com hipoglicemia"
            statusText.setTextColor(Color.RED)
        } else {
            statusText.setTextColor(Color.BLACK)
            statusText.text = "Tá tudo bem!"
        }
    }
}
