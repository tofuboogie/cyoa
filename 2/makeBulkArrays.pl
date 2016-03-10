#!/usr/bin/perl
use warnings;
use strict;

our $num = 0;
our $temp;
our @dt;
our $dt;
our @items;
our $items;
our @tz;
our $tz;

	our $rooms = join "",<STDIN>;

	#put tz rooms in array
	if ($rooms =~ /\/\/TZ's rooms(.*)\/\/DT's rooms/is) { $temp = $1; }
	else { print "no tz\n"; }
	@tz = ($temp =~ /\s*?(\w+)[,;]/ig);
	#print "Result = @tz\n\n";
	
	#put dt rooms in array
	if ($rooms =~ /\/\/DT's rooms(.*)\/\/items/is) { $temp = $1; }
	else { print "no dt\n"; }
	@dt = ($temp =~ /\s*?(\w+)[,;]/ig);
	#print "Result = @dt\n\n";
	
	#put items in array
	if ($rooms =~ /\/\/items(.*)\/\/persons/is) { $temp = $1; }
	else { print "no items\n"; }
	@items= ($temp =~ /\s*?(\w+)[,;]/ig);
	#print "Result = @items\n\n";
	
	
	print "package dtz.cyoa;
class Rooms_bulk extends Rooms {

	public static void initBulkArrays(){
		tz_rooms = new Room[] {";
		
	for (my $i=0;$i<$#tz;$i++){
		print "$tz[$i],";
	}
	print "$tz[$#tz]";
	
	print "};

		dt_rooms = new Room[] {";
	
	for (my $i=0;$i<$#dt;$i++){
		print "$dt[$i],";
	}
	print "$dt[$#dt]";	

	print "};

		items = new Item[] {";
	
	for (my $i=0;$i<$#items;$i++){
		print "$items[$i],";
	}
	print "$items[$#items]";
	
	print "};
	}
	
	public static void changeFieldReferences(){ // bc Java has no iYourself**
		changeFieldReferences_tz();
		changeFieldReferences_dt();
		changeFieldReferences_items();
	}
	
	public static void changeFieldReferences_tz() {\n";
	
	for (my $i=0;$i<=$#tz;$i++){
		print "\t\t$tz[$i] = tz_rooms[$i];\n";
	}
	print "\t}
		
	public static void changeFieldReferences_dt() {\n";
	
	for (my $i=0;$i<=$#dt;$i++){
		print "\t\t$dt[$i] = dt_rooms[$i];\n";
	}
	print "\t}
	
	public static void changeFieldReferences_items() {\n";
	
	for (my $i=0;$i<=$#items;$i++){
		print "\t\t$items[$i] = items[$i];\n";
	}
	print "\t}\n}";
	
	
	
	
	
		
		
	
	

