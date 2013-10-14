package org.sacredheart.commutebuddy;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;



	@Root(strict=false, name="service")
	public class Service
	{
		@Element(name="responsecode")
		private int responsecode;
		@Element(name="timestamp")
		private String timestamp;
		@ElementList(entry="line", name="subway")
		private List<Line> subway;
		@ElementList(entry="line", name="bus")
		private List<Line> bus;
		@ElementList(entry="line", name="BT")
		private List<Line> bt;
		@ElementList(entry="line", name="LIRR")
		private List<Line> lirr;
		@ElementList(entry="line", name="MetroNorth")
		private List<Line> metroNorth;
		
		public List<Line> getMnObject() {
			return metroNorth;
		}
	}

	@Root(strict=false, name="line")
	class Line
	{
		@Element(name="name")
		private String name;
				
		@Element(name="status")
		private String status;
						
		@Element(name="text", required=false)
		private String text;
		
		@Element(required=false, name="url")
		private String url;
					
		@Element(required=false, name="date")
		private String date;
		
		@Element(required=false, name="time")
		private String time;
		
		public String getName() {
			return name;
		}
		public String getStatus() {
			return status;
		}
		public String getText() {
			return text;
		}
		public String getDate() {
			return date;
		}
		public String getTime() {
			return time;
		}
	}