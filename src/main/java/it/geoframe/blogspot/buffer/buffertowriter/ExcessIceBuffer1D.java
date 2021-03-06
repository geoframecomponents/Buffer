/*
 * GNU GPL v3 License
 *
 * Copyright 2016 Marialaura Bancheri
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.geoframe.blogspot.buffer.buffertowriter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import oms3.annotations.*;

@Description("Buffer for 1D Richards simulation.")
@Documentation("")
@Author(name = "Niccolo' Tubini, Riccardo Rigon", contact = "tubini.niccolo@gmail.com")
@Keywords("Hydrology, Richards, Infiltration")
//@Label(JGTConstants.HYDROGEOMORPHOLOGY)
//@Name("shortradbal")
//@Status(Status.CERTIFIED)
@License("General Public License Version 3 (GPLv3)")

public class ExcessIceBuffer1D {

	@Description("Varible to store")
	@In 
	@Unit ("-")
	public ArrayList<double[]> inputVariable;

	@Description("Date at which the varible is computed")
	@In 
	@Unit ("YYYY-MM-DD HH:mm")
	public String inputDate;

	@Description("Boolean value controlling the buffer component")
	@In 
	@Unit ("-")
	public boolean doProcessBuffer;
	
	
	@In 
	public int writeFrequency = 1;

	//	@Description("Spatial coordinate: is the position of the centroids ")
	//	@In 
	//	@Unit ("m")
	//	public double[] inputSpatialCoordinate;
	//	
	//	@Description("Dual spatial coordinate: is the position of volumes' interfaces ")
	//	@In 
	//	@Unit ("m")
	//	public double[] inputDualSpatialCoordinate;


	@Description()
	@Out
	@Unit ()
	public LinkedHashMap<String,ArrayList<double[]>> myVariable = new LinkedHashMap<String,ArrayList<double[]>>(); // consider the opportunity to save varibale as float instead of double

	@Description()
	@Out
	@Unit ()
	public double[] mySpatialCoordinate;

	@Description()
	@Out
	@Unit ()
	public double[] myDualSpatialCoordinate;

	@Description("")
	int step=0;

	ArrayList<double[]> tempVariable;



	@Execute
	public void solve() {
//		System.out.println("Buffer1D step:" + step);
		if(step==0){

			//		mySpatialCoordinate = inputSpatialCoordinate;
			//		myDualSpatialCoordinate = inputDualSpatialCoordinate;

			tempVariable = new ArrayList<double[]>();
			//System.out.println(mySpatialCoordinate.toString());

		}
		

		if( ((step-1)%writeFrequency) == 0 || step == 1) {
//			System.out.println("Buffer1D clear");

			myVariable.clear();

		}

		if(doProcessBuffer== true) {
//			System.out.println("Buffer1D doProcessBuffer:" + doProcessBuffer);

			// z
			tempVariable.add(inputVariable.get(0).clone());

			// temperature
			tempVariable.add(inputVariable.get(1).clone());
			//		for(int i=0; i<tempVariable.get(1).length; i++) {
			//			System.out.println(tempVariable.get(1)[i]);
			//		}
			// theta_w
			tempVariable.add(inputVariable.get(2).clone());

			// theta_i
			tempVariable.add(inputVariable.get(3).clone());

			// water excess volume
			tempVariable.add(inputVariable.get(4).clone());

			// ice excess volume
			tempVariable.add(inputVariable.get(5).clone());

			// errorEnergy
			tempVariable.add(inputVariable.get(6).clone());

			// errorVolume
			tempVariable.add(inputVariable.get(7).clone());

			// waterFromNirvana
			tempVariable.add(inputVariable.get(8).clone());

			// waterToNirvana
			tempVariable.add(inputVariable.get(9).clone());

			// energyFromNirvana
			tempVariable.add(inputVariable.get(10).clone());

			// energyToNirvana
			tempVariable.add(inputVariable.get(11).clone());

			// surfaceElevation
			tempVariable.add(inputVariable.get(12).clone());
			
			// KMAX
			tempVariable.add(inputVariable.get(13).clone());

			// heat flux at the top of the  domain 
			tempVariable.add(inputVariable.get(14).clone());

			// heat flux at the bottom of the  domain
			tempVariable.add(inputVariable.get(15).clone());
			
			// soil erosion deposition
			tempVariable.add(inputVariable.get(16).clone());
			
			// ice erosion deposition
			tempVariable.add(inputVariable.get(17).clone());

			myVariable.put(inputDate,(ArrayList<double[]>) tempVariable.clone());
			//System.out.println(myVariable.size() +"       "+ myVariable.keySet());
			//System.out.println(myVariable.toString());
			tempVariable.clear();
		}
		step++;
//		System.out.println("Buffer1D completed step: "+step);

		

	}


}
