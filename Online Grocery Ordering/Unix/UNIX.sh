1.
sort -t ',' -k 2 input_file.csv
2.
sed -i 's/old_product_name/new_product_name/g' product_list.txt
3.
grep "search_product_name" product_list.txt
grep -i "search_product_name" product_list.txt
grep -w "search_product_name" product_list.txt
