#!/usr/bin/env python27
# -*- coding: utf8 -*-
# PYTHON 2.7

import xml.etree.ElementTree as ET
	

ficI = raw_input('>fichier (freesons.xml): ')
ficO = open(".\dicoDon.html", "w")
html = ""

if(ficI == ""):
	ficI = "D:\\Documents\\cours\\stages\\freesons\\freesons.xml"

print('\n'+ficI+'\n')

tree = ET.parse(ficI)
root = tree.getroot()

ficO.write("<html><head><title></title></head><body>\n")

for entite in root.iter('entite'):
	html = "\t<div id=\"liste-wrapper\"><label>"+ entite.get('name')+ ":</label>\n<ul>"
	ficO.write( html )

	for att in entite.findall('attribut'):
		if(att != None):
			ficO.write( '\t<li>' + att.get('name') + ' :\t' + att.get('type') + '\t' + att.get('key') + '</li>' )
	ficO.write('</ul></div>')

ficO.write("</body></html>")

ficO.close()

print(u"- Fichier créé -")