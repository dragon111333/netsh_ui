package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BackProcess {
	
	public ArrayList<String> getWifiName() throws Exception{ 
		ArrayList<String> wifiList = new ArrayList<String>();
		Process process = Runtime.getRuntime().exec("netsh wlan show profile");
		BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		while ((line=buf.readLine())!=null) {
			line = line.replaceAll("\\s","");
			if(line.length()>14&&line.substring(0,14).equals("AllUserProfile")) {
				wifiList.add(line.substring(15,line.length()));
			}
		}
		return wifiList;
	}
	

	public ArrayList<String> getWifiDataByName(String wifiName) throws Exception{ 
		ArrayList<String> wifiList = new ArrayList<String>();
		Process process = Runtime.getRuntime().exec("netsh wlan show profile \""+wifiName+"\" key=clear");
		BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		int i = 0;
		while ((line=buf.readLine())!=null) {
			line = line.replaceAll("\\s","");
			if(line.indexOf(':')>0 && i>1) {
				wifiList.add(line);
			}
			i++;
		}
		return wifiList;
	}
	
	public String[][] getWifiDataByNameArray(String wifiName) throws Exception{
		ArrayList<String> list = this.getWifiDataByName(wifiName);
		String newList[][] = new String[list.size()][2];
		int i = 0;
		for(String dataPack : list) {
			String data[] = dataPack.split(":");
			if(data.length==2) {
				newList[i][0] = data[0];
				newList[i][1] = data[1];
				i++;
			}
		}
		return newList;
	}
	
	public String[][] getWifiNameArray() throws Exception{
		ArrayList<String> list = this.getWifiName();
		String[][] listAr = new String[list.size()][1];
		int index = 0;
		for(String name : list) {
			listAr[index][0] = name;
			index++;
		}
		return listAr;
	}
	
}
