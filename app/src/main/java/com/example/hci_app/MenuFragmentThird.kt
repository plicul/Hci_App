package com.example.hci_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hci_app.databinding.FragmentMenuThirdBinding
import CategoryAdapter
import android.util.Log

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MenuFragmentThird : Fragment() {

    private var _binding: FragmentMenuThirdBinding? = null

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var category2Adapter: CategoryAdapter
    private lateinit var category3Adapter: CategoryAdapter

    private var lastSelectedPositionFirstRow = -1
    private var lastSelectedPositionSecondRow = -1

    //timer , clicker
    private var startTime: Long = 0
    private var itemsClicked: Int = 0
    //tasks
    val tasks: ArrayList<ArrayList<Task>> = ArrayList()
    private var difficulty = -1
    private var currentTask = 0
    private var startTestFlag = false
    //log
    private var xcl: Excel = Excel();

    //data za menue
    //data_easy[razina][1,2,3,4,5...]
    //data_medium[razina][1,2,3,4,5...]
    //data_hard[razina][1,2,3,4,5...]
    //    val data: ArrayList<ArrayList<String>> = ArrayList()
    val data_easy: ArrayList<DataModel> = ArrayList()
    val data_medium: ArrayList<DataModel> = ArrayList()
    val data_hard: ArrayList<DataModel> = ArrayList()
    var data: ArrayList<DataModel> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressTxt.text="0/15"
        setupData()
        setupButtons()
        setupTasks()
        setUpFirstMenuAdapter()
        setOnClickListeners()
    }
    private fun setupData() {
        //razina menija
        /*for (i in 0..2) {
            if(i==0){
                data_easy.add(DataModel("CAT0"))
                data_easy[i].addChild(DataModel("CAT00"))
                data_easy[i].addChild(DataModel("CAT01"))
                data_easy[i].addChild(DataModel("CAT02"))
                data_easy[i].addChild(DataModel("CAT03"))
                data_easy[i].addChild(DataModel("CAT04"))

                for(datamodel in data_easy[i].children){
                    if(datamodel.name=="CAT00"){
                        datamodel.addChild((DataModel("CAT000")))
                        datamodel.addChild((DataModel("CAT001")))
                    }
                    if(datamodel.name=="CAT01"){
                        datamodel.addChild((DataModel("CAT010")))
                        datamodel.addChild((DataModel("CAT011")))
                    }
                    if(datamodel.name=="CAT02"){
                        datamodel.addChild((DataModel("CAT020")))
                        datamodel.addChild((DataModel("CAT021")))
                    }
                    if(datamodel.name=="CAT03"){
                        datamodel.addChild((DataModel("CAT030")))
                        datamodel.addChild((DataModel("CAT031")))
                    }
                    if(datamodel.name=="CAT04"){
                        datamodel.addChild((DataModel("CAT040")))
                        datamodel.addChild((DataModel("CAT041")))
                    }
                }

            }
            if(i==1){
                data_easy.add(DataModel("CAT1"))
                data_easy[i].addChild(DataModel("CAT10"))
                data_easy[i].addChild(DataModel("CAT11"))
                data_easy[i].addChild(DataModel("CAT12"))
                data_easy[i].addChild(DataModel("CAT13"))
                data_easy[i].addChild(DataModel("CAT14"))

                for(datamodel in data_easy[i].children){
                    if(datamodel.name=="CAT10"){
                        datamodel.addChild((DataModel("CAT100")))
                        datamodel.addChild((DataModel("CAT101")))
                    }
                    if(datamodel.name=="CAT11"){
                        datamodel.addChild((DataModel("CAT110")))
                        datamodel.addChild((DataModel("CAT111")))
                    }
                    if(datamodel.name=="CAT12"){
                        datamodel.addChild((DataModel("CAT120")))
                        datamodel.addChild((DataModel("CAT121")))
                    }
                    if(datamodel.name=="CAT13"){
                        datamodel.addChild((DataModel("CAT130")))
                        datamodel.addChild((DataModel("CAT131")))
                    }
                    if(datamodel.name=="CAT14"){
                        datamodel.addChild((DataModel("CAT140")))
                        datamodel.addChild((DataModel("CAT141")))
                    }
                }

            }
            if(i==2){
                data_easy.add(DataModel("CAT2"))
                data_easy[i].addChild(DataModel("CAT20"))
                data_easy[i].addChild(DataModel("CAT21"))
                data_easy[i].addChild(DataModel("CAT22"))
                data_easy[i].addChild(DataModel("CAT23"))
                data_easy[i].addChild(DataModel("CAT24"))

                for(datamodel in data_easy[i].children){
                    if(datamodel.name=="CAT20"){
                        datamodel.addChild((DataModel("CAT200")))
                        datamodel.addChild((DataModel("CAT201")))
                    }
                    if(datamodel.name=="CAT21"){
                        datamodel.addChild((DataModel("CAT210")))
                        datamodel.addChild((DataModel("CAT211")))
                    }
                    if(datamodel.name=="CAT22"){
                        datamodel.addChild((DataModel("CAT220")))
                        datamodel.addChild((DataModel("CAT221")))
                    }
                    if(datamodel.name=="CAT23"){
                        datamodel.addChild((DataModel("CAT230")))
                        datamodel.addChild((DataModel("CAT231")))
                    }
                    if(datamodel.name=="CAT24"){
                        datamodel.addChild((DataModel("CAT240")))
                        datamodel.addChild((DataModel("CAT241")))
                    }
                }
            }
        }*/

        //40 ITEMA
        for (i in 0..2) {
            if(i==0){
                data_easy.add(DataModel("HOME"))
                data_easy[i].addChild(DataModel("HIGHLIGHT"))
                data_easy[i].addChild(DataModel("BOLD"))
                data_easy[i].addChild(DataModel("ITALIC"))
                data_easy[i].addChild(DataModel("UNDERLINE"))
                data_easy[i].addChild(DataModel("FONT"))
                data_easy[i].addChild(DataModel("FONT SIZE"))

                for(datamodel in data_easy[i].children){
                    if(datamodel.name=="HIGHLIGHT"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="BOLD"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="ITALIC"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="UNDERLINE"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="FONT"){
                        datamodel.addChild((DataModel("TNR")))
                        datamodel.addChild((DataModel("ARIAL")))
                        datamodel.addChild((DataModel("CALIBRI")))
                    }
                    if(datamodel.name=="FONT SIZE"){
                        datamodel.addChild((DataModel("+1")))
                        datamodel.addChild((DataModel("-1")))
                    }
                }

            }
            if(i==1){
                data_easy.add(DataModel("INSERT"))
                data_easy[i].addChild(DataModel("TABLE"))
                data_easy[i].addChild(DataModel("SHAPE"))
                data_easy[i].addChild(DataModel("PICTURE"))
                data_easy[i].addChild(DataModel("LINK"))
                data_easy[i].addChild(DataModel("SYMBOL"))

                for(datamodel in data_easy[i].children){
                    if(datamodel.name=="TABLE"){
                        datamodel.addChild((DataModel("2X2")))
                        datamodel.addChild((DataModel("2X3")))
                        datamodel.addChild((DataModel("3X2")))
                        datamodel.addChild((DataModel("3X3")))
                    }
                    if(datamodel.name=="SHAPE"){
                        datamodel.addChild((DataModel("CIRCLE")))
                        datamodel.addChild((DataModel("TRIANGLE")))
                        datamodel.addChild((DataModel("SQUARE")))
                    }
                    if(datamodel.name=="PICTURE"){
                        datamodel.addChild((DataModel("LOCAL")))
                        datamodel.addChild((DataModel("URL")))
                    }
                    if(datamodel.name=="LINK"){
                        datamodel.addChild((DataModel("WEB")))
                        datamodel.addChild((DataModel("DOCUMENT")))
                    }
                    if(datamodel.name=="SYMBOL"){
                        datamodel.addChild((DataModel("€")))
                        datamodel.addChild((DataModel("£")))
                        datamodel.addChild((DataModel("$")))

                    }
                }

            }
        }

        //60 razlicitih itema
        for (i in 0..2) {
            if(i==0){
                data_medium.add(DataModel("HOME"))
                data_medium[i].addChild(DataModel("HIGHLIGHT"))
                data_medium[i].addChild(DataModel("BOLD"))
                data_medium[i].addChild(DataModel("ITALIC"))
                data_medium[i].addChild(DataModel("UNDERLINE"))
                data_medium[i].addChild(DataModel("FONT"))
                data_medium[i].addChild(DataModel("FONT SIZE"))

                for(datamodel in data_medium[i].children){
                    if(datamodel.name=="HIGHLIGHT"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="BOLD"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="ITALIC"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="UNDERLINE"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="FONT"){
                        datamodel.addChild((DataModel("TNR")))
                        datamodel.addChild((DataModel("ARIAL")))
                        datamodel.addChild((DataModel("CALIBRI")))
                        datamodel.addChild((DataModel("COMIC SANS")))
                        datamodel.addChild((DataModel("PAPYRUS")))

                    }
                    if(datamodel.name=="FONT SIZE"){
                        datamodel.addChild((DataModel("+1")))
                        datamodel.addChild((DataModel("-1")))
                    }
                }

            }
            if(i==1){
                data_medium.add(DataModel("INSERT"))
                data_medium[i].addChild(DataModel("TABLE"))
                data_medium[i].addChild(DataModel("SHAPE"))
                data_medium[i].addChild(DataModel("PICTURE"))
                data_medium[i].addChild(DataModel("LINK"))
                data_medium[i].addChild(DataModel("SYMBOL"))

                for(datamodel in data_medium[i].children){
                    if(datamodel.name=="TABLE"){
                        datamodel.addChild((DataModel("2X2")))
                        datamodel.addChild((DataModel("2X3")))
                        datamodel.addChild((DataModel("3X2")))
                        datamodel.addChild((DataModel("3X3")))
                    }
                    if(datamodel.name=="SHAPE"){
                        datamodel.addChild((DataModel("CIRCLE")))
                        datamodel.addChild((DataModel("TRIANGLE")))
                        datamodel.addChild((DataModel("SQUARE")))
                    }
                    if(datamodel.name=="PICTURE"){
                        datamodel.addChild((DataModel("LOCAL")))
                        datamodel.addChild((DataModel("URL")))
                    }
                    if(datamodel.name=="LINK"){
                        datamodel.addChild((DataModel("WEB")))
                        datamodel.addChild((DataModel("DOCUMENT")))
                    }
                    if(datamodel.name=="SYMBOL"){
                        datamodel.addChild((DataModel("€")))
                        datamodel.addChild((DataModel("£")))
                        datamodel.addChild((DataModel("¥")))
                        datamodel.addChild((DataModel("$")))
                        datamodel.addChild((DataModel("≥")))
                        datamodel.addChild((DataModel("≤")))
                    }
                }

            }
            if(i==2){
                data_medium.add(DataModel("LAYOUT"))
                data_medium[i].addChild(DataModel("MARGINS"))
                data_medium[i].addChild(DataModel("ORIENTATION"))
                data_medium[i].addChild(DataModel("SIZE"))

                for(datamodel in data_medium[i].children){
                    if(datamodel.name=="MARGINS"){
                        datamodel.addChild((DataModel("NORMAL")))
                        datamodel.addChild((DataModel("NARROW")))
                        datamodel.addChild((DataModel("MODERATE")))
                        datamodel.addChild((DataModel("WIDE")))
                    }
                    if(datamodel.name=="ORIENTATION"){
                        datamodel.addChild((DataModel("PORTRAIT")))
                        datamodel.addChild((DataModel("LANDSCAPE")))
                    }
                    if(datamodel.name=="SIZE"){
                        datamodel.addChild((DataModel("LETTER")))
                        datamodel.addChild((DataModel("LEGAL")))
                        datamodel.addChild((DataModel("EXECUTIVE")))
                        datamodel.addChild((DataModel("A4")))
                        datamodel.addChild((DataModel("A5")))
                    }
                }
            }
        }
        //100 itema,
        for (i in 0..2) {
            if(i==0){
                data_hard.add(DataModel("HOME"))
                data_hard[i].addChild(DataModel("HIGHLIGHT"))
                data_hard[i].addChild(DataModel("BOLD"))
                data_hard[i].addChild(DataModel("ITALIC"))
                data_hard[i].addChild(DataModel("UNDERLINE"))
                data_hard[i].addChild(DataModel("FONT"))
                data_hard[i].addChild(DataModel("FONT SIZE"))
                data_hard[i].addChild(DataModel("FONT COLOR"))
                data_hard[i].addChild(DataModel("ALIGNMENT"))

                for(datamodel in data_hard[i].children){
                    if(datamodel.name=="HIGHLIGHT"){
                        datamodel.addChild((DataModel("RED")))
                        datamodel.addChild((DataModel("BLUE")))
                        datamodel.addChild((DataModel("GREEN")))
                        datamodel.addChild((DataModel("YELLOW")))
                        datamodel.addChild((DataModel("PING")))
                        datamodel.addChild((DataModel("BLACK")))
                        datamodel.addChild((DataModel("WHITE")))
                        datamodel.addChild((DataModel("PURPLE")))
                    }
                    if(datamodel.name=="BOLD"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="ITALIC"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="UNDERLINE"){
                        datamodel.addChild((DataModel("YES")))
                        datamodel.addChild((DataModel("NO")))
                    }
                    if(datamodel.name=="FONT"){
                        datamodel.addChild((DataModel("TNR")))
                        datamodel.addChild((DataModel("ARIAL")))
                        datamodel.addChild((DataModel("CALIBRI")))
                        datamodel.addChild((DataModel("COMIC SANS")))
                        datamodel.addChild((DataModel("PAPYRUS")))
                    }
                    if(datamodel.name=="FONT SIZE"){
                        datamodel.addChild((DataModel("8")))
                        datamodel.addChild((DataModel("9")))
                        datamodel.addChild((DataModel("10")))
                        datamodel.addChild((DataModel("11")))
                        datamodel.addChild((DataModel("12")))
                        datamodel.addChild((DataModel("14")))
                        datamodel.addChild((DataModel("16")))
                        datamodel.addChild((DataModel("18")))
                        datamodel.addChild((DataModel("20")))
                        datamodel.addChild((DataModel("24")))
                    }
                    if(datamodel.name=="FONT COLOR"){
                        datamodel.addChild((DataModel("RED")))
                        datamodel.addChild((DataModel("BLUE")))
                        datamodel.addChild((DataModel("GREEN")))
                        datamodel.addChild((DataModel("YELLOW")))
                        datamodel.addChild((DataModel("PING")))
                        datamodel.addChild((DataModel("BLACK")))
                        datamodel.addChild((DataModel("WHITE")))
                        datamodel.addChild((DataModel("PURPLE")))

                    }
                    if(datamodel.name=="ALIGNMENT"){
                        datamodel.addChild((DataModel("LEFT")))
                        datamodel.addChild((DataModel("CENTRE")))
                        datamodel.addChild((DataModel("RIGHT")))
                        datamodel.addChild((DataModel("JUSTIFY")))
                    }
                }

            }
            if(i==1){
                data_hard.add(DataModel("INSERT"))
                data_hard[i].addChild(DataModel("TABLE"))
                data_hard[i].addChild(DataModel("SHAPE"))
                data_hard[i].addChild(DataModel("PICTURE"))
                data_hard[i].addChild(DataModel("LINK"))
                data_hard[i].addChild(DataModel("SYMBOL"))
                data_hard[i].addChild(DataModel("CHART"))

                for(datamodel in data_hard[i].children){
                    if(datamodel.name=="TABLE"){
                        datamodel.addChild((DataModel("2X2")))
                        datamodel.addChild((DataModel("2X3")))
                        datamodel.addChild((DataModel("3X2")))
                        datamodel.addChild((DataModel("3X3")))
                    }
                    if(datamodel.name=="SHAPE"){
                        datamodel.addChild((DataModel("CIRCLE")))
                        datamodel.addChild((DataModel("TRIANGLE")))
                        datamodel.addChild((DataModel("SQUARE")))
                    }
                    if(datamodel.name=="PICTURE"){
                        datamodel.addChild((DataModel("LOCAL")))
                        datamodel.addChild((DataModel("URL")))
                    }
                    if(datamodel.name=="LINK"){
                        datamodel.addChild((DataModel("WEB")))
                        datamodel.addChild((DataModel("DOCUMENT")))
                    }
                    if(datamodel.name=="SYMBOL"){
                        datamodel.addChild((DataModel("€")))
                        datamodel.addChild((DataModel("£")))
                        datamodel.addChild((DataModel("¥")))
                        datamodel.addChild((DataModel("$")))
                        datamodel.addChild((DataModel("≥")))
                        datamodel.addChild((DataModel("≤")))
                    }
                    if(datamodel.name=="CHART"){
                        datamodel.addChild((DataModel("BAR")))
                        datamodel.addChild((DataModel("COLUMN")))
                        datamodel.addChild((DataModel("LINE")))
                        datamodel.addChild((DataModel("PIE")))
                    }
                }

            }
            if(i==2){
                data_hard.add(DataModel("LAYOUT"))
                data_hard[i].addChild(DataModel("MARGINS"))
                data_hard[i].addChild(DataModel("ORIENTATION"))
                data_hard[i].addChild(DataModel("SIZE"))
                data_hard[i].addChild(DataModel("INDENT LEFT"))
                data_hard[i].addChild(DataModel("INDENT RIGHT"))

                for(datamodel in data_hard[i].children){
                    if(datamodel.name=="MARGINS"){
                        datamodel.addChild((DataModel("NORMAL")))
                        datamodel.addChild((DataModel("NARROW")))
                        datamodel.addChild((DataModel("MODERATE")))
                        datamodel.addChild((DataModel("WIDE")))
                    }
                    if(datamodel.name=="ORIENTATION"){
                        datamodel.addChild((DataModel("PORTRAIT")))
                        datamodel.addChild((DataModel("LANDSCAPE")))
                    }
                    if(datamodel.name=="SIZE"){
                        datamodel.addChild((DataModel("LETTER")))
                        datamodel.addChild((DataModel("LEGAL")))
                        datamodel.addChild((DataModel("EXECUTIVE")))
                        datamodel.addChild((DataModel("A3")))
                        datamodel.addChild((DataModel("A4")))
                        datamodel.addChild((DataModel("A5")))
                    }
                    if(datamodel.name=="INDENT LEFT"){
                        datamodel.addChild((DataModel("+1")))
                        datamodel.addChild((DataModel("-1")))
                    }
                    if(datamodel.name=="INDENT RIGHT"){
                        datamodel.addChild((DataModel("+1")))
                        datamodel.addChild((DataModel("-1")))
                    }
                }
            }
        }

        data=data_easy

    }

    private fun setupButtons() {
        binding.btnEasy.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                startTimerEasy()
            }
        })
        binding.btnMedium.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                startTimerMedium()
            }
        })
        binding.btnHard.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                startTimerHard()
            }
        })
    }

    private fun setupTasks() {
        //tu za setup taskova
        for (i in 0..2) {
            tasks.add(ArrayList())
            if(i==0){
                tasks[i].add(Task(0,0,0)) // highlight yes  000
                tasks[i].add(Task(0,1,0)) // bold yes       010
                tasks[i].add(Task(0,4,1)) // font arial     041
                tasks[i].add(Task(1,0,1)) //2x3 table       101
                tasks[i].add(Task(1,2,0)) //picture local   120
                tasks[i].add(Task(0,2,0)) //italic yes      020
                tasks[i].add(Task(1,3,1)) //link document   131
                tasks[i].add(Task(1,2,1)) //picture web     121
                tasks[i].add(Task(1,4,0)) //symbol euro     140
                tasks[i].add(Task(1,4,1)) // symbol pound   141
                tasks[i].add(Task(0,0,0)) // highlight yes  000
                tasks[i].add(Task(1,0,0)) //2x2 table       100
                tasks[i].add(Task(0,4,1)) // font tnr       040
                tasks[i].add(Task(1,0,3)) //3x3 table       103
                tasks[i].add(Task(1,1,0)) // shape circle   110
            }
            if(i==1){
                tasks[i].add(Task(0,4,4)) // font papyrus   044
                tasks[i].add(Task(0,1,0)) // bold yes       010
                tasks[i].add(Task(0,4,1)) // font arial     041
                tasks[i].add(Task(1,4,4)) //symbol ≥        144
                tasks[i].add(Task(2,2,1)) //size legal      221
                tasks[i].add(Task(0,2,0)) //italic yes      020
                tasks[i].add(Task(1,3,1)) //link document   131
                tasks[i].add(Task(1,2,1)) //picture web     121
                tasks[i].add(Task(2,1,1)) //orient landscp  211
                tasks[i].add(Task(1,4,1)) // symbol pound   141
                tasks[i].add(Task(0,3,1)) // underline no   031
                tasks[i].add(Task(1,0,0)) //2x2 table       100
                tasks[i].add(Task(0,4,1)) // font tnr       040
                tasks[i].add(Task(2,0,2)) //margin narrow   202
                tasks[i].add(Task(1,1,0)) // shape circle   110
            }
            if(i==2){
                tasks[i].add(Task(1,3,1)) //link document   131
                tasks[i].add(Task(0,0,3)) //highlight yell  003
                tasks[i].add(Task(2,0,2)) //margins mdrt    202
                tasks[i].add(Task(1,4,4)) //symbol ≥        144
                tasks[i].add(Task(2,2,1)) //size legal      221
                tasks[i].add(Task(0,5,4)) //font size12     054
                tasks[i].add(Task(1,3,1)) //link document   131
                tasks[i].add(Task(0,6,7)) //fntclr prpl     067
                tasks[i].add(Task(2,1,1)) //orient landscp LAYOUT  211
                tasks[i].add(Task(1,4,1)) // symbol pound   141
                tasks[i].add(Task(1,5,3)) //chart pie       153
                tasks[i].add(Task(1,0,0)) //2x2 table       100
                tasks[i].add(Task(0,4,1)) // font tnr       040
                tasks[i].add(Task(2,0,2)) //margin narrow   202
                tasks[i].add(Task(1,1,0)) //indent right-1  241
            }

        }
    }


    private fun setOnClickListeners(){
        binding.rvCategory3.setOnClickListener{
            // Tu nisam sigurna koji menu kojeg zatvara ti ces to znati
            // Ako nisi siguran pitaj me
            if (binding.rvCategory3.visibility == View.VISIBLE){
                binding.rvCategory3.visibility = View.GONE
            }
        }
    }

    // Adapteri:
    private fun setUpFirstMenuAdapter() {
        val categoryList: ArrayList<String> = ArrayList()
        for(datamodel in data){
            categoryList.add(datamodel.name)
        }
        categoryAdapter = CategoryAdapter(
            categoryList,
            requireContext(),
            onItemClick = { position ->
                onFirstMenuClicked(position)
            }
        )
        binding.rvCategory1.adapter = categoryAdapter
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvCategory1.layoutManager = layoutManager
    }

    private fun setUpSecondMenuAdapter(firstRowPosition: Int) {
        val categoryList: ArrayList<String> = ArrayList()
        var counter = 0
        for(datamodel in data){
            if(counter==firstRowPosition){
                for(datamodelSecondRow in datamodel.children){
                    categoryList.add(datamodelSecondRow.name)
                }
            }
            counter++
        }
        category2Adapter = CategoryAdapter(
            categoryList,
            requireContext(),
            onItemClick = { secondRowPosition ->
                onSecondMenuClicked(firstRowPosition, secondRowPosition)
            }
        )
        binding.rvCategory2.adapter = category2Adapter
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvCategory2.layoutManager = layoutManager
        binding.rvCategory2.scrollToPosition(5)
    }

    private fun setUpThirdMenuAdapter(firstRowPosition: Int, secondRowPosition: Int) {
        val categoryList: ArrayList<String> = ArrayList()
        var counterfirst=0
        var countersecond=0

        for(datamodel in data){
            if(counterfirst==firstRowPosition){
                for(datamodelSecondRow in datamodel.children){
                    if(countersecond==secondRowPosition)
                        for(datamodelThirdRow in datamodelSecondRow.children)
                            categoryList.add(datamodelThirdRow.name)
                    countersecond++
                }
            }
            counterfirst++
        }

        category3Adapter = CategoryAdapter(
            categoryList,
            requireContext(),
            onItemClick = {thirdRowPosition ->
                onThirdMenuClicked(firstRowPosition,secondRowPosition,thirdRowPosition)
            }
        )
        binding.rvCategory3.adapter = category3Adapter
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvCategory3.layoutManager = layoutManager
    }

    private fun onFirstMenuClicked(firstRowPosition: Int) {
        itemsClicked++
        if (firstRowPosition == lastSelectedPositionFirstRow) {
            binding.rvCategory2.visibility = View.GONE
            lastSelectedPositionFirstRow = -1
        } else {
            setUpSecondMenuAdapter(firstRowPosition)
            binding.rvCategory2.visibility = View.VISIBLE
            lastSelectedPositionFirstRow = firstRowPosition
        }
        binding.rvCategory3.visibility = View.GONE
        lastSelectedPositionSecondRow = -1
    }

    private fun onSecondMenuClicked(firstRowPosition: Int, secondRowPosition: Int) {
        itemsClicked++
        if (secondRowPosition == lastSelectedPositionSecondRow) {
            binding.rvCategory3.visibility = View.GONE
            binding.rvCategory1.visibility = View.VISIBLE
            lastSelectedPositionSecondRow = -1
        } else {
            setUpThirdMenuAdapter(firstRowPosition, secondRowPosition)
            binding.rvCategory3.visibility = View.VISIBLE
            binding.rvCategory1.visibility=View.GONE
            lastSelectedPositionSecondRow = secondRowPosition
        }

    }
    private fun onThirdMenuClicked(firstRowPosition: Int, secondRowPosition: Int, thirdRowPosition: Int) {
        itemsClicked++
        if(startTestFlag){
            if(tasks[difficulty][currentTask].equals(Task(firstRowPosition,secondRowPosition,thirdRowPosition))){
                val currentTime = System.currentTimeMillis()
                val timeElapsed = currentTime - startTime
                if(currentTask==14){
                    //isprintaj u file još jednom
                    Log.d("Timer", "Time elapsed: $timeElapsed" + " END")
                    xcl.addData(timeElapsed.toDouble(),currentTask+1, difficulty, itemsClicked)
                    xcl.saveFile()
                    //instanciraj novu datoteku za log
                    xcl=Excel()
                    //stavi current task u -1 i difficulty u -1
                    //hideaj sve
                    currentTask=-1
                    difficulty=-1
                    itemsClicked=0
                    binding.rvCategory1.visibility=View.VISIBLE
                    binding.rvCategory2.visibility=View.GONE
                    binding.rvCategory3.visibility=View.GONE
                    lastSelectedPositionFirstRow = -1
                    lastSelectedPositionSecondRow = -1
                    startTestFlag=false
                    binding.progressTxt.text = "15/15"

                    return
                }
                binding.rvCategory1.visibility=View.VISIBLE
                binding.rvCategory2.visibility=View.GONE
                binding.rvCategory3.visibility=View.GONE
                lastSelectedPositionFirstRow = -1
                lastSelectedPositionSecondRow = -1
                currentTask++
                binding.progressTxt.text = "$currentTask/15"
                //tu printaj u log file
                xcl.addData(timeElapsed.toDouble(),currentTask+1, difficulty, itemsClicked)
                itemsClicked=0
                Log.d("Timer", "Time elapsed: $timeElapsed")
            }
        }
    }

    public fun startTimerEasy() {
        startTime = System.currentTimeMillis()
        data=data_easy
        difficulty=0
        currentTask=0
        itemsClicked=0
        startTestFlag=true
        binding.progressTxt.text="0/15"
        binding.rvCategory2.visibility=View.GONE
        binding.rvCategory3.visibility=View.GONE
        setUpFirstMenuAdapter()
    }
    public fun startTimerMedium() {
        startTime = System.currentTimeMillis()
        data=data_medium
        difficulty=1
        currentTask=0
        itemsClicked=0
        startTestFlag=true
        binding.progressTxt.text="0/15"
        binding.rvCategory2.visibility=View.GONE
        binding.rvCategory3.visibility=View.GONE
        setUpFirstMenuAdapter()

    }
    public fun startTimerHard() {
        startTime = System.currentTimeMillis()
        data=data_hard
        difficulty=2
        currentTask=0
        itemsClicked=0
        startTestFlag=true
        binding.progressTxt.text="0/15"
        binding.rvCategory2.visibility=View.GONE
        binding.rvCategory3.visibility=View.GONE
        setUpFirstMenuAdapter()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}