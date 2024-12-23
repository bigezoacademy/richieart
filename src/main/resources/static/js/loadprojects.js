
                        // Function to fetch and display projects
                        function loadProjects() {
                            // Make an AJAX request to fetch projects from the server
                            $.ajax({
                                url: '/categories', // Replace with your server endpoint for getting projects
                                method: 'GET',
                                dataType: 'json',
                                success: function (projects) {
                                    console.log('Successfully fetched Projects:', projects);

                                    // Clear existing content within myprojectcategories
                                    $('.myprojectcategories').empty();

                                    // Counter variable to track row numbers
                                    var counter = 1;

                                    // Populate the container with fetched projects
                                    projects.forEach(function (project) {
                                        console.log('show project:', project);

                                        // Create and append a button for each project
                                        var projectButton = $('<button class="m-1 btn  btn-dark"></button>')
                                            .text(project)
                                            .click(function () {
                                                // Handle button click if needed
                                                console.log('Button clicked:', project);
                                            });

                                        // Append button to myprojectcategories
                                        $('.myprojectcategories').append(projectButton);

                                        // Increment the counter
                                        counter++;
                                    });
                                },

                                error: function (error) {
                                    console.error('Error fetching Projects', error);
                                }
                            });
                        }

                        // Call loadProjects when the 'Projects' button is clicked
                        $('.sidenavoption[data-target-content="projects"]').on('click', function () {
                            console.log("Button clicked, starting AJAX request...");
                            changeOption(this); // Assuming this function shows/hides the appropriate content
                            loadProjects();     // Load projects dynamically
                            console.log("AJAX request initiated.");
                        });
