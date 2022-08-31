package com.ms.in.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.ms.in.entity.Specialization;

public class SpecializationExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model,
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		//1. define your own file name
		response.addHeader("Content-Disposition","attachment;filename=SPEC.xlsx");
		
		//2. read data by controller
		List <Specialization> list = (List<Specialization>) model.get("list");
		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("Specialization");
		
		//4. create row #0 as header
		setHaad(sheet);
		
		//5. create row #1 onwords from List<T>
		setBody(sheet,list);
	}

	private void setHaad(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(0).setCellValue("CODE");
		row.createCell(0).setCellValue("NAME");
		row.createCell(0).setCellValue("NOTES");
	}

	private void setBody(Sheet sheet, List<Specialization> list) {
		int rowNum=1;
		for(Specialization spec :list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getSpecId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNotes());
	       }
	  }	
}
