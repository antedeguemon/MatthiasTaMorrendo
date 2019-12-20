package pl.vicente.matthiastamorrendo


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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
                    statusText.text = glicoses.last().date
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
}
