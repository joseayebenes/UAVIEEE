package ieee.joseantonio.uavieee;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsolaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsolaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsolaFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ListView list;
    EditText et_comando;
    ImageButton btn_enviar;

    /**
     * Array adapter for the conversation thread
     */
    public ArrayAdapter<String> adapterView;


    // TODO: Rename and change types and number of parameters
    public static ConsolaFragment newInstance() {
        ConsolaFragment fragment = new ConsolaFragment();
        return fragment;
    }

    public ConsolaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consola, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView)view.findViewById(R.id.listView);
        btn_enviar = (ImageButton)view.findViewById(R.id.imageButton);
        et_comando = (EditText)view.findViewById(R.id.editText);

        // Initialize the array adapter for the conversation thread
        adapterView = new ArrayAdapter<String>(getActivity(), R.layout.message);
        list.setAdapter(adapterView);

        // Initialize the compose field with a listener for the return key
        et_comando.setOnEditorActionListener(mWriteListener);

        // Initialize the send button with a listener that for click events
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    String message = et_comando.getText().toString();
                    et_comando.setText("");
                    sendMessage(message);
                }
            }
        });

    }

    /**
     * The action listener for the EditText widget, to listen for the return key
     */
    private TextView.OnEditorActionListener mWriteListener
            = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
            // If the action is a key-up event on the return key, send the message
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String message = view.getText().toString();
                view.setText("");

                sendMessage(message);
            }

            return true;
        }
    };

    private void sendMessage(String message) {
        addListItem(">> "+message);
        mListener.onFragmentInteraction(message);
    }

    public void addListItem(String str){
        adapterView.add(str);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String str);
    }

}
