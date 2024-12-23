
                        // Function to fetch and display categories
                        function loadCategories() {
                            // Make an AJAX request to fetch categories from the server
                            $.ajax({
                                url: '/categories', // Replace with your server endpoint for getting categories
                                method: 'GET',
                                dataType: 'json',
                                success: function (projects) {
                                    console.log('Successfully fetched Projects:', projects);

                                    // Clear existing table rows
                                    $('#categoriesTableBody').empty();

                                    // Counter variable to track row numbers
                                    var counter = 1;

                                    // Populate the table with fetched projects
                                    projects.forEach(function (project) {
                                        console.log('show project:', project);

                                        // Create table row
                                        var row = $('<tr></tr>');

                                        // Create and append table cells
                                        row.append('<td>' + counter + '</td>');
                                        console.log(counter + ' ----this is yo id');
                                        row.append('<td>' + project + '</td>'); // Use project directly
                                        console.log(project + ' ----this is yo category');
                                     //   var optionsCell = $('<td></td>');

                                        // Create delete button
                                        var deleteButton = $('<button class="m-1 border-1 px-3 py-1"></button>')
                                            .click(function () {
                                                confirmDelete(counter); // Pass row number as an argument
                                            })
                                            .html('<i class="bi me-1 bi-trash"></i> Delete');

                                        // Create edit button
                                        var editButton = $('<button class="m-1 bg-dark text-light px-3 py-1"></button>')
                                            .click(function () {
                                                editCategory(project);
                                            })
                                            .html('<i class="bi me-1 bi-pencil-square"></i> Edit');

                                        // Append buttons to optionsCell
                                        //optionsCell.append(deleteButton);
                                      //  optionsCell.append(editButton);

                                        // Append optionsCell to the row
                                      //  row.append(optionsCell);

                                        // Append row to the table
                                        $('#categoriesTableBody').append(row);

                                        // Increment the counter
                                        counter++;
                                    });
                                },

                                error: function (error) {
                                    console.error('Error fetching Categories', error);
                                }
                            });
                        }

                        // Call loadCategories when the 'Categories' button is clicked
                        $('.sidenavoption[data-target-content="categories"]').on('click', function () {
                            console.log("Button clicked, starting AJAX request...");
                            changeOption(this); // Assuming this function shows/hides the appropriate content
                            loadCategories();   // Load categories dynamically
                            console.log("AJAX request initiated.");
                        });
